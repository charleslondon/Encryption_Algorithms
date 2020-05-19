package rsa;

import java.util.Scanner;

public class Driver 
{
	public static void main(String[] args)
	{
		Person bob = new Person();
		Person alice = new Person();

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String msg = "x";
		System.out.print("Please enter the message you wish to send: ");
		
		while(msg.length() > 0)
		{
			System.out.println();
			msg = scanner.nextLine();
			System.out.println("Plaintext Message is : " + msg);
			System.out.print("Encoded Message is   : ");
			RSA.show(RSA.toLong(msg));
			long[] cipher = bob.encryptTo(msg, alice);
			System.out.println();
			System.out.println ("Bob Encrypts the message as : ");
			RSA.show(cipher);
			System.out.println ("Alice decrypts and reads: " + alice.decrypt(cipher));	
			System.out.println();
			System.out.println();
			System.out.println("---------------------------------------------------------");
			System.out.println();
			System.out.println();
			System.out.println("To terminate, please enter an empty string.");
			System.out.print("Please enter the message you wish to send: ");
		}

		scanner.close();
	}
}
