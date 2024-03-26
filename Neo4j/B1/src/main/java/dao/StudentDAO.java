package dao;

import entity.Student;
import org.neo4j.driver.*;
import org.neo4j.driver.types.Node;
import utils.AppUtils;

import java.util.List;
import java.util.Map;

public class StudentDAO {
    private Driver driver;

    public StudentDAO() {
        driver = AppUtils.getDriver();
    }

    /**
     * Liệt kê doanh sách n sinh viên
     */
    public List<Student> list10Students(int n) {
        String query = "MATCH (s:Student) RETURN s LIMIT $n";
        try (Session session = driver.session()) {
            return session.executeRead(tx -> {
                Result rs = tx.run(query, Values.parameters("n", n));
                if (!rs.hasNext()) {
                    return null;
                }
                return rs.stream()
                        .map(record -> record.get("s").asNode())
                        .map(node -> AppUtils.nodeToPOJO(node, Student.class))
                        .toList();
            });
        }
    }

    /**
     * Tìm sinh viên khi biết mã số
     */
    public Student timSVTheoMa(String ma) {
        String query = "Match (student:Student) where student.studentID = $id return student";
        Map<String,Object> pars = Map.of("id", ma);
        try (Session session = driver.session()) {
            return session.executeRead(tx -> {
                Result rs = tx.run(query, pars);
                if (!rs.hasNext()){
                    return null;
                }
                Node node = rs.single().get("student").asNode();
                return AppUtils.nodeToPOJO(node, Student.class);
            });

        }
    }
    /**
     * Liệt kê danh sách các tên của các sinh viên đăng ký học khóa học CS101
     * MATCH (student:Student)-[:ENROLLED]->(course:Course)
     * WHERE course.courseID = "CS101"
     * RETURN student.name
     */
    public List<String> dsSVCS101(String ma){
        String query = "Match (s:Student)-[:ENROLLED]->(c:Course) where c.courseID = $id return s.name";
        Map<String,Object> pars = Map.of("id", ma);
        try(Session session = driver.session()){
            return session.executeRead(tx->{
                Result rs = tx.run(query, pars);
                if (!rs.hasNext()){
                    return null;
                }
                return rs.stream()
                        .map(record -> record.get("s.name").toString()).toList();
            });
        }
    }
    /**
     * 18. Danh sách sinh viên có gpa >= 3.2, kết quả sắp xếp giảm dần theo gpa
     */
    public List<Student> ds32(){
        String query = "Match (s:Student) where s.gpa >= 3.2 return s  order by s.gpa desc";
        try(Session session = driver.session()){
            return session.executeRead(tx->{
                Result result = tx.run(query);
                if (!result.hasNext()){
                    return null;
                }
                return result.stream()
                        .map(record -> record.get("s").asNode())
                        .map(node -> AppUtils.nodeToPOJO(node, Student.class)).toList();
            });
        }
    }
    public void closed() {
        driver.close();
    }
}
