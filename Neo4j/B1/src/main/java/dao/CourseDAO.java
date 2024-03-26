package dao;

import entity.Course;
import entity.Department;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.SessionConfig;
import utils.AppUtils;

import org.neo4j.driver.Driver;

import java.util.List;
import java.util.Map;

public class CourseDAO {
    private Driver driver;
    private SessionConfig sessionConfig;

    public CourseDAO(Driver driver, String db) {
        this.driver = driver;
        sessionConfig = SessionConfig.builder().withDatabase(db).build();
    }

    /**
     * Thêm khóa học vào khoa IE: IE202, Simulation, 3 hours
     * return c
     * match (d:Department {deptID: "IE"}) create (c:Course {courseID: "IE203", name: "Simulation", hours : 3})-[:BELONGS_TO]->(d) return c
     *
     * @return true
     */
    public boolean themKhoaHoc(Course course) {
        String query = "MATCH (dept:Department {deptID: 'IE'}) " +
                "CREATE (course:Course {courseID: $courseID, name: $name, hours: $hours})-[:BELONGS_TO]->(dept) " +
                "RETURN course";
        Map<String, Object> pars = AppUtils.getProperties(course);
        try (Session session = driver.session(sessionConfig)) {
            return session.writeTransaction(tx -> {
                Result result = tx.run(query, pars);
                return result.hasNext();
            });
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Xóa khóa học theo mã
     *
     * @param ma
     */
    public void Delete(String ma) {
        String query = "Match (course:Course{courseID: $id}) DETACH delete course";
        Map<String, Object> pars = Map.of("id", ma);
        try (Session session = driver.session()) {
            session.executeWrite(tx -> tx.run(query, pars).consume());
        }
    }

    /**
     * Xóa toàn bộ các khóa học
     */

    public void closed() {
        driver.close();
    }
}
