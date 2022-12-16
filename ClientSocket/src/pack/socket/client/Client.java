package pack.socket.client;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import pack.socket.model.*;

public class Client {
	public static void main(String[] args) throws Exception {
		Client.connect();
	}
	
	private static void connect() throws Exception {
		Socket s = new Socket("localhost", 1234);
		
		DataInputStream dis = new DataInputStream(s.getInputStream());
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter ID :");
		String ID = scan.nextLine();
		System.out.print("Enter the Name:");
		String Name = scan.nextLine();
		System.out.println("Enter the Description:");
		String Desc = scan.nextLine();
		Customer customer = new Customer(ID, Name, Desc);
		System.out.println("Send information ...");
		
		ObjectOutputStream oos = new ObjectOutputStream(dos);
		oos.writeObject(customer);
		
		System.out.println("Receive file from server...");
		byte[] bytes = new byte[1024*2];
		int byteLen;
		FileOutputStream fos = new FileOutputStream("C:/Users/miumu/Downloads/customer"
				+ customer.getId()+".txt");
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		while ((byteLen = dis.read(bytes, 0, bytes.length))!=-1) {
			bos.write(bytes, 0, byteLen);
			bos.flush();
		}
		bos.close();
		System.out.println("Done.");
		
		s.close();
	}
}
