package server;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import dao.StudentDAO;
import dao.impl.StudentImpl;
import entity.Student;

public class Server {
	public static void main(String[] args) {
		
		
		try(ServerSocket server = new ServerSocket(7878)){
			
			System.out.println("Server is listening on port 7878");
			
			while (true) {
				Socket socket = server.accept();
				System.out.println("Client connected");
				System.out.println("Client address: " + socket.getInetAddress().getHostName());
				
				Thread t = new Thread(new ClientHandler(socket));
				t.start();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

class ClientHandler implements Runnable{
	private Socket socket;
	private StudentDAO studentDAO;

	public ClientHandler(Socket socket) {
		super();
		this.socket = socket;
		studentDAO = new StudentImpl();
	}

	@Override
	public void run() {
		
		try {
			
			DataInputStream in = new DataInputStream(socket.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			
			
			int choice = 0;
			
			while(true) {
				choice = in.readInt();
				switch (choice) {
				case 1: 
					String title = in.readUTF();
					List<Student> students = studentDAO.findStudentsEnrolledInCourse(title);
					
//					response to client
					
					out.writeObject(students);
					break;
				case 2:
					int year = in.readInt();
					System.out.println("Year: " + year);
					students = studentDAO.findStudentsEnrolledInYear(year);
					students.forEach(System.out::println);
					out.writeObject(students);
					break;
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
