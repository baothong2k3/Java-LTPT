/*
 * @ (#) TestCourse.java    1.0    02/04/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package TestDAO;/*
 * @description:
 * @author: Bao Thong
 * @date: 02/04/2024
 * @version: 1.0
 */

import dao.CourseDAO;
import entity.Course;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestCourse {
    /**
     * @description: Set up before class
     */
    private CourseDAO courseDAO;
    @BeforeAll
    void setUp() {
        courseDAO = new CourseDAO();
    }
    @Test
    void testListAllCourses() {
        List<Course> courses = courseDAO.listAllCourses();
        assertNotNull(courses, "The returned list should not be null");
        assertFalse(courses.isEmpty(), "The returned list should not be empty");
        courses.forEach(System.out::println);
    }
    @Test
    void testListOfCourseByCredit() {
        List<Course> courses = courseDAO.listOfCourseByCredit(3);
        assertNotNull(courses, "The returned list should not be null");
        assertFalse(courses.isEmpty(), "The returned list should not be empty");
        courses.forEach(System.out::println);
    }
    @AfterAll
    void tearDown() {
        courseDAO.close();
        courseDAO = null;
    }
}
