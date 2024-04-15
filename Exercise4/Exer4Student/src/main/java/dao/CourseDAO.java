package dao;

import java.util.List;

import entity.Course;

public interface CourseDAO {
	public boolean add(Course course);
	public boolean update(Course course);
	public boolean delete(int id);
	public Course findById(int id);
	public List<Course> findAll();
	public List<Course> findBytitle(String title); //Tim tuong doi
	public Course findBytitle2(String title); //Tim tuyet doi
	
}
