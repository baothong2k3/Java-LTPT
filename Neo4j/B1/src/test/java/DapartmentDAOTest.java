import dao.DepartmentDAO;
import entity.Course;
import entity.Department;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.neo4j.driver.Driver;
import utils.AppUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DapartmentDAOTest {
    private DepartmentDAO departmentDAO;
    private static final String db = "thong21014091";


    @BeforeAll
    void setUp() {
        departmentDAO = new DepartmentDAO(AppUtils.getDriver(),db);
    }

    @Test
    void findListCourse() {
        List<Course> courseList = departmentDAO.listCourseKhiBietMaKhoa("MATH");
        assertNotNull(courseList);
        assertEquals(3, courseList.size());
        courseList.forEach(System.out::println);
    }

    @Test
    void updateByID() {
        assertEquals(true, departmentDAO.updateDept("Mathematics", "Math"));
    }

    @Test
    void updateByMs() {
        assertEquals("Rock n Roll", departmentDAO.updateDept("Rock n Roll", "Music"));
    }

    @Test
    void allKhoa() {
        List<Department> d = departmentDAO.dsKhoa();
        assertNotNull(d);
        assertEquals(5, d.size());
        d.forEach(System.out::println);
    }

    @Test
    void allTen() {
        List<String> ten = departmentDAO.dsTen();
        assertNotNull(ten);
        assertEquals(5, ten.size());
        ten.forEach(System.out::println);
    }

    @Test
    void CSIE() {
        List<String> csie = Arrays.asList("IE", "CS");
        List<Course> courseList = departmentDAO.listCoursesForMultipleDepts(csie);
        assertEquals(5, courseList.size());
        assertNotNull(courseList);
        courseList.forEach(System.out::println);
    }
    @Test
    void demSV(){
        Map<String,Long> counts = departmentDAO.countStudentsInEachDept();
        counts.forEach((key, value) -> System.out.println("Department ID: " + key + ", Student Count: " + value));
        assertNotNull(counts);
    }
    @Test
    void demSVOrder(){
        Map<String, Long> counts = departmentDAO.countStudentsInEachDeptOrder();
        counts.forEach((key,value)-> System.out.println("Department ID: " + key + ", Student Count: " + value));
        assertNotNull(counts);
    }
    @Test
    void demSVOrderByN(){
        Map<String, Long> counts = departmentDAO.countStudentsInEachDeptOrderByQuantity();
        //counts.forEach(count -> System.out.println("Department: " + count.get("deptID") + " Student: " + count.get("quantity")));
        counts.forEach((key,value)-> System.out.println("Department ID: " + key + ", Student Count: " + value));
        assertNotNull(counts);
    }
    @Test
    void Lk0SV(){
        List<String> ds = departmentDAO.getDeansOfDeptsWithNoStudents();
        ds.forEach(System.out::println);
        assertNotNull(ds);
        assertEquals(2,ds.size());
    }
    @Test
    void dsNhieu(){
        List<Map<String,Object>> ds = departmentDAO.getDeptWithMostStudents();
        assertNotNull(ds);
        ds.forEach(System.out::println);
    }
    @AfterAll
    void tearDown() {
        departmentDAO.closed();
    }
}
