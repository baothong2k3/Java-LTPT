package client;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

import entity.Course;

public class Client {
	public static void main(String[] args) {

		try (Socket socket = new Socket("H92M17", 1234);
				Scanner sc = new Scanner(System.in);
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				) {

			while (true) {
				System.out.println("Enter your chosing: \n1. Search course by title: \n2.Serach course by ID: ");
				int choose = sc.nextInt();
				out.writeInt(choose);
				switch (choose) {
				case 1:
					sc.nextLine();
					System.out.println("Enter the course's title: ");
					String title = sc.nextLine();
					out.writeUTF(title);
					out.flush();
					
					List<Course> courses = (List<Course>) in.readObject();
					courses.forEach(x -> System.out.println(x));
					break;

				case 2:
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
