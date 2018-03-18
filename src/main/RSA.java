package main;

import java.math.*;
import java.util.Random;

public class RSA {
	private BigInteger n_mstr;			//Public key 1 for in comm Base
	private BigInteger e_mstr;			//Public key 2 for in comm Exponent (Encryption key)
	
	private BigInteger n_slv;			//Public key 1 for out comm Base
	private BigInteger e_slv;			//Public key 2 for out comm Exponent (Encryption key)
	
	private BigInteger d;				//Private key  Exponent (Decryption key)
	private BigInteger mul = BigInteger.TEN.pow(38);
	
	public RSA (){					//Constructor (Runs when new RSA is created)
		BigInteger p, q, t;
		p = BigInteger.probablePrime(63, new Random());
		q = BigInteger.probablePrime(63, new Random());
		
		n_mstr = p.multiply(q);
		t = n_mstr.subtract(p).subtract(q).add(BigInteger.ONE);
		
		e_mstr=BigInteger.probablePrime(31, new Random());
		d=e_mstr.modInverse(t);
	}
	
	public BigInteger[] GetKeys(){
		BigInteger[] k = {n_mstr,e_mstr};
		return k;
	}
	
	public BigInteger EncryptMstr(long m){
		return EncryptMstr(BigInteger.valueOf(m));
	}
	
	public BigInteger EncryptMstr(BigInteger M){
		return M.modPow(e_mstr, n_mstr).multiply(mul).add(CalcHash(M));
	}
	
	public BigInteger DecryptMstr(BigInteger m){
		 BigInteger[] div = m.divideAndRemainder(mul);
		 BigInteger hashMes = hashSlv(div[1]);
		 System.out.println(hashMes);
		 BigInteger Mes = div[0].modPow(d, n_mstr);
		 if(BigInteger.valueOf(Math.abs(Mes.toString(2).hashCode())).equals(hashMes)){
			 System.out.println("Correct");
			 return Mes;
		 }
		return BigInteger.valueOf(-1);
	}
	
	public BigInteger CalcHash(BigInteger arg1){
		return BigInteger.valueOf(Math.abs(arg1.toString(2).hashCode())).modPow(d, n_mstr);
	}
	
	public BigInteger hashMstr(BigInteger arg){
		return arg.modPow(e_mstr, n_mstr);
	}
	
	//As Slave
	public void SetKeys (BigInteger[] keyIn ){
		n_slv = keyIn[0];
		e_slv = keyIn[1];
	}
	
	public BigInteger EncryptSlv(long m){
		return EncryptSlv(BigInteger.valueOf(m));
	}
	
	public BigInteger EncryptSlv(BigInteger M){
		return M.modPow(e_slv, n_slv).multiply(mul).add(CalcHash(M));
	}
	
	public BigInteger hashSlv(BigInteger arg){
		return arg.modPow(e_slv, n_slv);
	}
}	