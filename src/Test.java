import main.RSA;

import java.math.BigInteger;

public class Test {
	public static void main(String[] args) throws Exception{
		RSA Alice = new RSA();
		RSA Bob = new RSA();

		Alice.SetKeys(Bob.GetKeys());
		//System.out.println("Bob gave his lock to Alice!!");
		Bob.SetKeys(Alice.GetKeys());
		//System.out.println("Alice gave her lock to Bob!!");
		
		BigInteger i = new BigInteger("12345678901");
		BigInteger hash = Alice.CalcHash(i);
		BigInteger alhash = Alice.hashMstr(hash);
		BigInteger bohash = Bob.hashSlv(hash);
		System.out.println("hash "+hash + "\nAlHash "+alhash +"\nBoHash "+ bohash);
		
		
		System.out.println("Alice reads " + i);					// Alice reads i
		BigInteger c = Alice.EncryptSlv(i);						// Alice encrypts i
		System.out.println("Alice's encrypted message = " + c);	// Alice's encrypted i
		System.out.println(hash.toString().length());
		BigInteger d = Bob.DecryptMstr(c);						// Bob decrypts Alice's message
		System.out.println("Bob gets " + d);					// Bob gets i (hopefully!!)
		 

		
	}
}
