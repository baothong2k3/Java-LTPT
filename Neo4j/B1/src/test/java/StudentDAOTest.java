import dao.StudentDAO;
import entity.Student;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentDAOTest {
    private StudentDAO studentDAO;

    @BeforeAll
    void setup() {
        studentDAO = new StudentDAO();
    }

    @Test
    void listSV(){
        List<Student> list = studentDAO.list10Students(3);
        assertNotNull(list);
        assertEquals(3, list.size());
        list.forEach(System.out::println);
    }
    @Test
    void findSV(){
        Student student = studentDAO.timSVTheoMa("22");
        assertNotNull(student);
        assertEquals("22", student.getStudentID());
        assertEquals("Sanders", student.getName());
        System.out.println(student);
    }
    @Test
    void dsTenSVDK(){
        List<String> ten = studentDAO.dsSVCS101("CS101");
        assertEquals(2, ten.size());
        assertNotNull(ten);
        ten.forEach(System.out::println);
    }
    @Test
    void sv32(){
        List<Student> list = studentDAO.ds32();
        assertNotNull(list);
        list.forEach(System.out::println);
    }
    @AfterAll
    void tearDown(){
        studentDAO.closed();
        studentDAO = null;
    }
}
