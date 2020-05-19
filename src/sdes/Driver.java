package sdes;

import java.util.*;
/**
 * Test the implementation of SDES
 * 
 * @author (sdb) 
 * @version (Sep 2010)
 */
public class Driver
{
	public static void main(String[] args)
	{   
		SDES sdes = new SDES();
		System.out.println("First, the requested demo input");

		byte[] demoCipher = {-115,-17,-47,-113,-43,-47,15,84,-43,-113,-17,84,-43,79,58,15,64,-113,-43,65,-47,
				127,84,64,-43,-61,79,-43,93,-61,-14,15,-43,-113,84,-47,127,-43,127,84,127,10,84,15,64,43};
		sdes.getKey10("0111111101");
		System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(demoCipher));
		System.out.println("Decrypted: " + sdes.byteArrayToString(sdes.decrypt(demoCipher)));

		System.out.println();
		System.out.println("Second, some arbitrary messages to prove functionality.");
		System.out.println();

		String dummyPlainText = "A basic message";
		sdes.getKey10("1010101010");
		byte[] dummyCipher = sdes.encrypt(dummyPlainText);
		System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(dummyCipher));
		System.out.println("Decrypted: " + sdes.byteArrayToString(sdes.decrypt(dummyCipher)));
		System.out.println();
		
		dummyPlainText = "A bit more advanced message!?! A tad longer as well.";
		sdes.getKey10("0000000000");
		dummyCipher = sdes.encrypt(dummyPlainText);
		System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(dummyCipher));
		System.out.println("Decrypted: " + sdes.byteArrayToString(sdes.decrypt(dummyCipher)));
		System.out.println();
		
		dummyPlainText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vel molestie nulla, vitae condimentum neque. "
				+ "Integer accumsan eros eu placerat auctor. Fusce in interdum metus. Vivamus tortor sem, cursus vel dui a, interdum ullamcorper velit."
				+ " Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nullam pharetra mauris eget ipsum elementum "
				+ "lacinia. Donec ultricies, lectus vitae consectetur accumsan, nulla ipsum volutpat sapien, id cursus dolor velit et justo."
				+ " Etiam dignissim lectus ante, id sodales nulla venenatis in. Curabitur vel tortor ligula.";
		sdes.getKey10("1111111111");
		dummyCipher = sdes.encrypt(dummyPlainText);
		System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(dummyCipher));
		System.out.println("Decrypted: " + sdes.byteArrayToString(sdes.decrypt(dummyCipher)));
		System.out.println();		
		
		System.out.println();
		System.out.println("Next the interactive console.");
		System.out.println();
		
		Scanner scanner = new Scanner(System.in);
		String plain = "x";

		while(plain.length() > 0)
		{
			System.out.println("Enter plain text, or hit 'Enter' to terminate");
			plain = scanner.nextLine();
			sdes.getKey10(scanner);
			byte[] interactiveCipher = sdes.encrypt(plain);

			System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(interactiveCipher));
			System.out.println("Decrypted: " + sdes.byteArrayToString(sdes.decrypt(interactiveCipher)));
			System.out.println();
		}
	}
}
