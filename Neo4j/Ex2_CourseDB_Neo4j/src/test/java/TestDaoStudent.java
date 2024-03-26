import fit.dev.sin.dao.DaoSinhVien;
import fit.dev.sin.entities.Student;
import fit.dev.sin.util.AppUtils;
import org.junit.jupiter.api.*;

import java.util.List;

/**
 * @ (#) TestDaoStudent.java      3/20/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/20/2024
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestDaoStudent {
    private DaoSinhVien daoSinhVien;

    private static final String DB_NAME = "coursedb";
    @BeforeAll
    public void setUp() {
        daoSinhVien = new DaoSinhVien(AppUtils.getDriver(),DB_NAME);
    }

    @Test
    public void testListOfStudent() {
        int n = 3;
        List<Student> list = daoSinhVien.listOfStudent(n);
        Assertions.assertNotNull(list);
        Assertions.assertEquals(n, list.size());
        list.forEach(System.out::println);
    }

    @Test
    public void testFindStudentByID() {
        String id = "11";
        Student student = daoSinhVien.findStudentByID(id);
        Assertions.assertNotNull(student);
        Assertions.assertEquals(id, student.getId());
        System.out.println(student);
    }

    @Test
    public void testListOfStudentGpa() {
        List<Student> list = daoSinhVien.listOfStudentGpa();
        Assertions.assertNotNull(list);
        list.forEach(System.out::println);
    }


    @AfterAll
    public void downtear(){
        daoSinhVien.close();
    }
}
