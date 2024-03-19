package demo;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import com.google.gson.Gson;

import dao.ProductDao;
import entity.Product;

public class Server {
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
				
				Server server = new Server();
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
		private ProductDao productDao;
		
		public Handler(Socket socket) {
			super();
			this.socket = socket;
			productDao = new ProductDao();
		}

		@Override
		public void run() {
			
			Gson gson = new Gson();
			
			try (DataInputStream in = new DataInputStream(socket.getInputStream());
//					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
					){
				while(true) {
					String categoryName = in.readUTF();
					System.out.println("Server received: " + categoryName);
					
					List<Product> products = productDao.listOfProductsByCategory(categoryName);
					out.writeObject(products);
					
					
//					String json = gson.toJson(products);
//					out.writeUTF(json);
					
					
					
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
