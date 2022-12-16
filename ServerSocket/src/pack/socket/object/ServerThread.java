package pack.socket.object;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import pack.socket.model.Customer;


public class ServerThread implements Runnable{
	
	private Socket s = null;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	public ServerThread (Socket s) {
		try {
			this.s = s;
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			ObjectInputStream ois = new ObjectInputStream(dis);
			Customer customer = (Customer) ois.readObject();
			String path = "C:/Users/miumu/Documents/customer" + customer.getId() + ".txt";
			System.out.println(path);
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			String text  = "ID: " + customer.getId() + "\n" 
							+"Name: " + customer.getName() + "\n"
							+"Desc: " + customer.getDesc();
			bw.write(text);
			System.out.println("Had create a file in path.");
			bw.close();
			
			System.out.println("Send file to client...");
			File file = new File(path);
			BufferedInputStream bis =new BufferedInputStream(new FileInputStream(file));
			
			byte[] bytes = new byte[1024*2];
			int len;
			while ((len = bis.read(bytes))!=-1) {
				dos.write(bytes);
			}
			System.out.println("Done.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
