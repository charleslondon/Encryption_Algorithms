package rsa;

/**
 * Public-key encryption implementation.
 * 
 * @author Charles London
 * @author Josh Golding
 * @author Jacob Kershaw
 * 
 * @date 11/17/2016
 */
public class RSA
{

	public RSA()
	{
	}

	/**
	 * Find the multiplicative inverse of a long int.
	 * 
	 * @param e
	 * @param m
	 * @return The inverse of e, mod m. Uses the extended Euclidean Algorithm
	 */
	public static long inverse(long e, long m)
	{
		long x = 0, y = 1, preX = 1, preY = 0, temp, mod = m;

		while(m != 0)
		{
			long q = e / m;
			long r = e % m;

			e = m;
			m = r;

			temp = x;
			x = preX - q * x;
			preX = temp;

			temp = y;
			y = preY - q * y;
			preY = temp;
		}

		if(preX < 0)
		{
			preX += mod;
		}
		
		return preX;
	}

	/**
	 * Display an array of longs on stdout.
	 * 
	 * @param cipher
	 */
	public static void show(long[] cipher)
	{
		for (long l : cipher) {
			System.out.print(l + " ");
		}

		System.out.println();
	}

	/**
	 * Raise a number, b, to a power, p, modulo m.
	 * 
	 * @param b
	 * @param p
	 * @param m
	 * @return (b^p) mod m.
	 */
	public static long modPower(long b, long p, long m)
	{
		long e = p;
		long result = b % m;

		while(e > 1)
		{
			result = (result * b) % m;
			e--;
		}

		return result;
	}

	/**
	 * Find a random prime number.
	 * 
	 * @param m
	 * @param n
	 * @param rand
	 * @return A random prime in the range m..n, using rand to generate the
	 *         number.
	 */
	public static long randPrime(int m, int n, java.util.Random rand)
	{
		int rnd = rand.nextInt(n - m) + m;

		while(!(isPrime(rnd)))
		{
			rnd = rand.nextInt(n - m) + m;
		}

		return rnd;
	}

	
	/**
	 * 
	 * @param n
	 * @return
	 */
	private static boolean isPrime(int n)
	{
		for(int i = 2; i <= Math.sqrt(n); i++)
		{
			if((n % i) == 0)
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * Find a random number relatively prime to a given long int.
	 * 
	 * @param n
	 * @param rand
	 * @return A random number relatively prime to n.
	 */
	public static long relPrime(long n, java.util.Random rand)
	{
		long possibleAnswer = rand.nextInt(2000);
		
		while(gcd(n, possibleAnswer) != 1)
		{
			possibleAnswer = rand.nextInt(2000);
		}
		
		return possibleAnswer;
	}

	private static long gcd(long a, long b)
	{
	    long t;
	    
	    while(b != 0)
	    {
	        t = a;
	        a = b;
	        b = t % b;
	    }
	    
	    return a;
	}
	
	
	/**
	 * Convert two numeric chars to long integer.
	 * 
	 * @param msg
	 * @return the two digit number beginning at position p of msg as a long integer.
	 */
	public static long[] toLong(String msg) // TODO
	{	
		if((msg.length() % 2) != 0)
		{
			msg += " ";
		}
		
		long[] result = new long[msg.length() / 2];

		for(int i = 0; i < result.length; i = (i + 2))
		{
			result[i] = msg.charAt(i);
			result[i] = (result[i] << 8);
			result[i] += msg.charAt(i + 2);
		}

		return result;
	}

	/**
	 * Convert a long[] to a string.
	 * 
	 * @param x
	 * @return The string in ascii of the long[]
	 */
	public static String longToChars(long[] x) // TODO
	{
		StringBuilder result = new StringBuilder();

		for (long l : x) {
			result.append((char) l);
		}

		return result.toString();
	}
}
