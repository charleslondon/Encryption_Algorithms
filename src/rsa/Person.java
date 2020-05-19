package rsa;

/**
 *  Used to test public-key crypto-system, RSA.
 * 
 * @author Charles London
 * @author Josh Golding
 * @author Jacob Kershaw
 * 
 * @date 11/17/2016
 */
public class Person 
{
	long m;
	long e;
	long d;
	long n;

	/**
	 * Generate a public key for this person, consisting of
	 * exponent e, and modulus m.
	 * 
	 * Generate a private key for this person, consisting of
	 * an exponent d. Provide access to the public key only.
	 */
	public Person()
	{
		java.util.Random random = new java.util.Random();
		long p = RSA.randPrime(1, 2000, random);
		long q = RSA.randPrime(1, 2000, random);
		
		m = p * q;
		n = (p - 1) * (q - 1);
		e = RSA.relPrime(n, random);
		d = RSA.inverse(e, n);
	}


	/**
	 * Decrypt the cipher text.
	 * 
	 * @param cipher
	 * @return A string of plain text.
	 */
	public String decrypt(long[] cipher)
	{
		long[] result = new long[cipher.length];

		for(int i = 0; i < cipher.length; i++)
		{
			result[i] = RSA.modPower(cipher[i], d, m);
		}

		return RSA.longToChars(result);
	}


	/**
	 * Encrypt the plain text message to she.
	 * 
	 * @param msg
	 * @param recipient
	 * @return An array of longs, which is the cipher text.
	 */
	public long[] encryptTo(String msg, Person recipient)
	{
		long e = recipient.getE();
		long m = recipient.getM();
		long[] result = RSA.toLong(msg);

		for(int i = 0; i < result.length; i++)
		{
			result[i] = RSA.modPower(result[i], e, m);
		}

		return result;
	}


	/**
	 * Access to the public encryption exponent.
	 * 
	 * @return The public encryption exponent for this Person.
	 */
	public long getE()
	{
		return e;
	}


	/**
	 * Access to the public modulus.
	 * 
	 * @return The public modulus for this Person
	 */
	public long getM()
	{
		return m;
	}
}
