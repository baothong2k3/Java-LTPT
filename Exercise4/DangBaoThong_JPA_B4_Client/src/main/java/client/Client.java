/*
 * @ (#) Client.java    1.0    09/04/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package client;/*
 * @description:
 * @author: Bao Thong
 * @date: 09/04/2024
 * @version: 1.0
 */

import entity.Course;
import entity.Student;
import entity.StudentGrade;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 7878);
             Scanner sc = new Scanner(System.in);) {

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            int choice = 0;
            while (true) {
                System.out.println("Enter your choice: \n"
                        + "1. Find students enrolled in a course\n"
                        + "2. Find students enrolled in a year\n"
                        + "3. Add new person\n"
                        + "4. List of courses by credit");
                choice = sc.nextInt();
                sc.nextLine();
                out.writeInt(choice);
                switch (choice) {
                    case 1:
                        sc.nextLine();
                        System.out.println("Enter the course title: ");
                        String title = sc.nextLine();

                        out.writeUTF(title);

                        List<StudentGrade> students = (List<StudentGrade>) in.readObject();
                        students.forEach(System.out::println);
                        break;
                    case 2:
                        System.out.println("Enter the year: ");
                        int year = sc.nextInt();

                        out.writeInt(year);

                        List<Student> students2 = (List<Student>) in.readObject();
                        students2.forEach(System.out::println);
                        break;
                    case 3:
                        System.out.println("Enter the first name: ");
                        String firstname = sc.nextLine();
                        out.writeUTF(firstname);
                        System.out.println("Enter the last name: ");
                        String lastname = sc.nextLine();
                        out.writeUTF(lastname);
                        boolean result = in.readBoolean();
                        if (result) {
                            System.out.println("Person added successfully");
                        } else {
                            System.out.println("Failed to add person");
                        }
                        break;
                    case 4:
                        System.out.println("Enter the credit: ");
                        int credit = sc.nextInt();
                        out.writeInt(credit);
                        List<Course> courses = (List<Course>) in.readObject();
                        courses.forEach(System.out::println);
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
