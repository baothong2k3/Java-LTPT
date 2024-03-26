import fit.dev.sin.dao.DaoCourse;
import fit.dev.sin.entities.Course;
import fit.dev.sin.util.AppUtils;
import org.junit.jupiter.api.*;

import java.util.List;

/**
 * @ (#) TestDaoCourse.java      3/20/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/20/2024
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestDaoCourse {
    private DaoCourse daoCourse;

    private static final String DB_NAME = "coursedb";

    @BeforeAll
    public void setUp() {
        daoCourse = new DaoCourse(AppUtils.getDriver(),DB_NAME);
    }

    @Test
    public void testListOfCourse() {
        String dept = "CS";
        List<Course> list = daoCourse.listOfCourseByDeptID("CS");
        Assertions.assertNotNull(list);
        list.forEach(System.out::println);
    }

    @Test
    public void testCreateCourseByDept() {
        String dept = "IE";
        Course course = new Course("IE202","Simulation",3,null);
        Course c = daoCourse.createCourseByDept(dept, course);
        Assertions.assertNotNull(c);
        System.out.println(c);
    }

    @Test
    public void testListOfCourseByDeptID() {
        String deptCS = "CS";
        List<Course> list = daoCourse.listOfCourseByDeptID(deptCS);
        Assertions.assertNotNull(list);
        System.out.println("Khoa hoc cua khoa " + deptCS + " la:");
        list.forEach(System.out::println);

        String deptIE = "IE";
        List<Course> listIE = daoCourse.listOfCourseByDeptID(deptIE);
        Assertions.assertNotNull(list);
        System.out.println("Khoa hoc cua khoa " + deptIE + " la:");
        listIE.forEach(System.out::println);
    }

    @AfterAll
    public void downtear(){
        daoCourse.close();
    }
}
