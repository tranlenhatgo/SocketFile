package pack.socket.server;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import pack.socket.object.ServerThread;

public class Server {
	public static void main(String[] args) throws Exception {
		Server.serve();
	}
	
	private static void serve() throws Exception {
		ServerSocket ss = new ServerSocket(1234);
		System.out.println("Server is running...");
		while (true) {
			Socket s = ss.accept();
			System.out.println("Get a client " + s);
			
			new Thread(new ServerThread(s)).start();
		}
	}
}
