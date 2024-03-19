package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	public static void main(String[] args) {
		
		try (Socket socket = new Socket("192.168.178.19", 7878);
				Scanner sc = new Scanner(System.in);
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				DataInputStream in = new DataInputStream(socket.getInputStream());
			){
			while(true) {
				System.out.println("Enter your message: ");
				String message = sc.nextLine(); //Hi
				out.writeUTF(message);
				out.flush();
				
//				Received message from server
				message = in.readUTF();
				System.out.println("Received from Server: " + message);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
