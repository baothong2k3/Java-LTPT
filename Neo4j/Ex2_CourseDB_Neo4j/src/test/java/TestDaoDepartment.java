import fit.dev.sin.dao.DaoDepartment;
import fit.dev.sin.dao.DaoSinhVien;
import fit.dev.sin.entities.Department;
import fit.dev.sin.util.AppUtils;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;

/**
 * @ (#) TestDaoDepartment.java      3/20/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/20/2024
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestDaoDepartment {
    private DaoDepartment daoDepartment;

    private static final String DB_NAME = "coursedb";

    @BeforeAll
    public void setUp() {
        daoDepartment = new DaoDepartment(AppUtils.getDriver(), DB_NAME);
    }

    @Test
    public void testUpdateDepartmentByName() {
        String id = "Music";
        String name = "Rock n Roll";
        Department dept = daoDepartment.updateDepartmentByName(name, id);
        Assertions.assertEquals(name, dept.getName());
        System.out.println(dept);
    }

    @Test
    public void testGetAllDean() {
        List<String> dept = daoDepartment.getAllNameDean();
        Assertions.assertNotNull(dept);
        dept.forEach(System.out::println);
    }

    @Test
    public void testGetDeanByID() {
        String id = "CS";
        String dean = daoDepartment.getDeanByDeptID(id);
        Assertions.assertNotNull(dean);
        System.out.println(dean);
    }

    @Test
    public void testSumQuantitySVOfDept() {
        Map<String, Integer> map = daoDepartment.sumQuantitySVOfDept();
        Assertions.assertNotNull(map);
        map.forEach((k,v)-> System.out.println(k + " : " + v));
    }

    @Test
    public void testSumQuantitySVOfDeptSortDeptID() {
        Map<String, Integer> map = daoDepartment.sumQuantitySVOfDeptSortDeptID();
        Assertions.assertNotNull(map);
    }

    @Test
    public void testListOfNameDeanNoSt() {
        List<String> list = daoDepartment.listOfNameDeanNoSt();
        Assertions.assertNotNull(list);
        list.forEach(System.out::println);
    }

    @Test
    public void testListOfDeptStdMax() {
        Map<Department, Integer> map = daoDepartment.listOfDeptStdMax();
        Assertions.assertNotNull(map);
        map.forEach((k,v)-> System.out.println(k + " -- Quantity_Student: " + v));
    }

    @AfterAll
    public void downtear() {
        daoDepartment.close();
    }
}
