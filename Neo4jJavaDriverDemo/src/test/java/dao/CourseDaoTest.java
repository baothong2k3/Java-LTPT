package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.Course;
import util.AppUtils;

public class CourseDaoTest {
	private static final String DB_NAME = "coursedb";
	private CourseDao courseDao;
	
	@BeforeEach
	public void setup() {
		courseDao = new CourseDao(AppUtils.initDriver(),DB_NAME);
	}
	
//	@Test
//	public void testAddCourse2() {
//		Course course = new Course("CS500", "Java Programming", 3);
//		
//		String id = courseDao.addCourse2(course);
//		
//		assertNotNull(id);
//		String expected = "CS500";
//		assertEquals(expected , id);
//	}
	
	@Test
	public void testfindCourseByID() {
		String id = "CS500";
		Course course = courseDao.findCourseByID(id);
		assertNotNull(course);
		assertEquals(id, course.getCourseId());
		assertEquals("Java Programming", course.getName());
		assertEquals(3, course.getHours());
	}
	
	@Test
	public void testfindCourseByID_NotFound() {
		String id = "CS555";
		Course course = courseDao.findCourseByID(id);
		assertNull(course, "Not found");
	}
	
	@AfterEach
	public void teardown() {
		courseDao.close();
		courseDao = null;
	}
}
