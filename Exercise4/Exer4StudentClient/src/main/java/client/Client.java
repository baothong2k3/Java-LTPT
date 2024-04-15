package client;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

import entity.Student;

public class Client {
	public static void main(String[] args) {
		
		try(Socket socket = new Socket("", 7878);
				
				Scanner sc = new Scanner(System.in);
				){
			
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			
			int choice = 0;
			
			while(true) {
				
				System.out.println("Enter your choice: \n"
						+ "1. Find students enrolled in a course\n"
						+ "2. Find students enrolled in a year");
				
				choice  = sc.nextInt();
				
				out.writeInt(choice);
				
				switch (choice) {
				case 1:
					sc.nextLine();
					System.out.println("Enter the course title: ");
					String title = sc.nextLine();
					
					out.writeUTF(title);
					out.flush();
					
//					Receive the results from the server
					
					List<Student> students = (List<Student>) in.readObject();
					students.forEach(System.out::println);
					
					break;

				case 2:
					System.out.println("Enter the year: ");
					int year = sc.nextInt();
					
					out.writeInt(year);
					out.flush();
					
					List<Student> students2 = (List<Student>) in.readObject();
					students2.forEach(System.out::println);
					
					break;
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}	
