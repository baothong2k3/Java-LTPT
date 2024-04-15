package dao;

import java.util.List;

import entity.Student;

public interface StudentDAO {
	public List<Student> findAll();
	public List<Student> findStudentsEnrolledInCourse(String title);
	public List<Student> findStudentsEnrolledInYear(int year);
}
