import dao.CourseDAO;
import entity.Course;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import utils.AppUtils;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CourseDAOTest {
    private CourseDAO courseDAO;
    private static final String db = "thong21014091";

    @BeforeAll
    void setUp() {
        courseDAO = new CourseDAO(AppUtils.getDriver(),db);
    }

    @Test
    void Them() {
        assertTimeoutPreemptively(Duration.ofSeconds(5), ()->{
            Course course = new Course("IE213", "S", 3);
            boolean isAdded = courseDAO.themKhoaHoc(course);
            assertEquals(true,isAdded);
        });
    }

    @Test
    void Xoa() {
        assertTimeoutPreemptively(Duration.ofSeconds(2), ()->{
            courseDAO.Delete("IE209");
        });
    }

    @AfterAll
    void tearDown() {
        courseDAO.closed();
    }
}
