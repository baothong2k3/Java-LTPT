package dao.impl;

import java.util.List;

import dao.StudentDAO;
import entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class StudentImpl implements StudentDAO {
	
private EntityManager em;
	
	public StudentImpl() {
		em = Persistence.createEntityManagerFactory("jpa-mssql")
				.createEntityManager();
	}

	@Override
	public List<Student> findAll() {
		return em.createNamedQuery("Student.findAll", Student.class).getResultList();
	}

	@Override
	public List<Student> findStudentsEnrolledInCourse(String title) {
		return em.createNamedQuery("student.findStudentsEnrolledInCourse", Student.class)
				.setParameter("title", "%"+title+"%")
				.getResultList();
	}

	@Override
	public List<Student> findStudentsEnrolledInYear(int year) {
		return em.createNamedQuery("Student.findByEnrollmentInYear", Student.class)
		.setParameter("year", year)
		.getResultList();
	}

}
