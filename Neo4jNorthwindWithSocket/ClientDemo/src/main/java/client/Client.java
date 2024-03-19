package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

import entity.Product;

public class Client {
	public static void main(String[] args) {
		
		try (Socket socket = new Socket("192.168.178.19", 7878);
				Scanner sc = new Scanner(System.in);
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
//				DataInputStream in = new DataInputStream(socket.getInputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				){
			while(true) {
				System.out.println("Enter the category name: ");
				String categoryName = sc.nextLine();  //Beverages
				out.writeUTF(categoryName);
				out.flush();
				
//				Received
				
				List<Product> products = (List<Product>)in.readObject();
				products.forEach(System.out::println);
				
//				String json = in.readUTF();
//				System.out.println(json);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
