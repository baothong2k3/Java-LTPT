package demo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {
	public static void main(String[] args) {
		
		ServerSocket serverSocket = null;
		
		try {
			
			serverSocket = new ServerSocket(7878);
			System.out.println("Server is running on port 7878");
			
			while(true) {
				Socket socket = serverSocket.accept();
				System.out.println("Client connected");
				System.out.println(socket.getInetAddress());
				System.out.println(socket.getPort());
				
				ChatServer server = new ChatServer();
				Handler handler = server.new Handler(socket);
				Thread thread = new Thread(handler);
				thread.start();
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private class Handler implements Runnable{
		
		private Socket socket;
		
		public Handler(Socket socket) {
			super();
			this.socket = socket;
		}

		@Override
		public void run() {
			
			try (DataInputStream in = new DataInputStream(socket.getInputStream());
					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					Scanner sc = new Scanner(System.in);
					){
				while(true) {
					String message = in.readUTF();
					System.out.println("Server received: " + message);
					
//					Server send message to client
					message = sc.nextLine();
					out.writeUTF(message);
					
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
