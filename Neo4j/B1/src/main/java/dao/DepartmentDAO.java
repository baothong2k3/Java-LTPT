package dao;

import entity.Course;
import entity.Department;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.SessionConfig;
import org.neo4j.driver.types.Node;
import utils.AppUtils;

import org.neo4j.driver.Driver;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.TreeMap;

public class DepartmentDAO {
    private Driver driver;
    private SessionConfig sessionConfig;

    public DepartmentDAO(Driver driver, String db) {
        this.driver = driver;
        sessionConfig = SessionConfig.builder().withDatabase(db).build();
    }

    /**
     * Tìm danh sách khóa học thuộc của một khoa nào đó khi biết mã khoa
     * deptID
     *
     * @return listCourse
     */
    public List<Course> listCourseKhiBietMaKhoa(String ma) {
        String query = "MATCH (course:Course) -[:BELONGS_TO]-> (dept:Department) WHERE toUpper(dept.deptID) = $id RETURN course;";
        Map<String, Object> pars = Map.of("id", ma);
        try (Session session = driver.session()) {
            return session.executeRead(tx -> {
                Result rs = tx.run(query, pars);
                if (!rs.hasNext()) {
                    return null;
                }
                return rs.stream()
                        .map(record -> record.get("course").asNode())
                        .map(node -> AppUtils.nodeToPOJO(node, Course.class)).toList();
            });
        }
    }

    /**
     * Cập nhật name = “Mathematics” cho department_id = “Math”
     */
    public boolean updateDept(String ten, String ma) {
        String query = "Match (dept:Department {deptID: $id}) set dept.name = $name return dept";
        Map<String, Object> pars = Map.of("id", ma, "name", ten);
        try (Session session = driver.session(sessionConfig)) {
            return session.executeWrite(tx -> {
                Result rs = tx.run(query, pars);
                return rs.hasNext();
            });
        } catch (Exception e){
            return false;
        }
    }

    /**
     * Cập nhật name = “Rock n Roll” cho department_id = “Music”
     */
    public Department updateNameMusic(String name, String id) {
        String query = "match (dept:Department) where dept.deptID= $ma set dept.name = $ten return dept";
        Map<String, Object> pars = Map.of("ma", id, "ten", name);
        try (Session session = driver.session()) {
            return session.executeWrite(tx -> {
                Result rs = tx.run(query, pars);
                if (!rs.hasNext()) {
                    return null;
                }
                Node node = rs.single().get("dept").asNode();
                return AppUtils.nodeToPOJO(node, Department.class);
            });
        }
    }

    /**
     * Liệt kê tất cả các khoa
     * MATCH (n:Department) RETURN n
     */
    public List<Department> dsKhoa() {
        String query = "Match (d:Department) return d";
        try (Session session = driver.session()) {
            return session.executeRead(tx -> {
                Result rs = tx.run(query);
                if (!rs.hasNext()) {
                    return null;
                }
                return rs.stream()
                        .map(record -> record.get("d").asNode())
                        .map(node -> AppUtils.nodeToPOJO(node, Department.class)).toList();
            });
        }
    }

    /**
     * Liệt kê tên của tất cả các trưởng khoa
     * MATCH (dept:Department) RETURN dept.dean
     *
     * @return list
     */
    public List<String> dsTen() {
        String query = "MATCH (dept:Department) RETURN dept.dean";
        try (Session session = driver.session()) {
            return session.executeRead(tx -> {
                Result rs = tx.run(query);
                if (!rs.hasNext()) {
                    return null;
                }
                return rs.stream()
                        .map(record -> record.get("dept.dean").toString()).toList();
            });
        }
    }

    /**
     * Liệt kê tất cả các khóa học của CS và IE
     * MATCH (course:Course) -[:BELONGS_TO]-> (dept:Department) WHERE toUpper(dept.deptID) IN ["IE", "CS"]  RETURN course;
     */
    public List<Course> listCoursesForMultipleDepts(List<String> deptIds) {
        String query = "MATCH (course:Course) -[:BELONGS_TO]-> (dept:Department) WHERE toUpper(dept.deptID) IN $ids RETURN course;";
        Map<String, Object> pars = Map.of("ids", deptIds);
        try (Session session = driver.session()) {
            return session.executeRead(tx -> {
                Result rs = tx.run(query, pars);
                if (!rs.hasNext()) {
                    return null;
                }
                return rs.stream()
                        .map(record -> record.get("course").asNode())
                        .map(node -> AppUtils.nodeToPOJO(node, Course.class)).toList();
            });
        }
    }

    /**
     * 13. Tổng số sinh viên đăng ký học của mỗi khoa
     * match (s:Student)-[:ENROLLED]->(c:Course)-[:BELONGS_TO]->(d:Department) return d.deptID as deptID, count(s) as quantity
     * Map<String, Long> countStudentsInEachDept()
     *
     */
    //C1
    public Map<String, Long> countStudentsInEachDept() {
        String query = "MATCH (s:Student)-[:ENROLLED]->(c:Course)-[:BELONGS_TO]->(d:Department) " +
                "RETURN d.deptID as deptID, count(s) as quantity";
        try (Session session = driver.session(sessionConfig)) {
            return session.readTransaction(tx -> {
                Result result = tx.run(query);
                if(!result.hasNext()){
                    return null;
                }
                return result.list().stream()
                        .collect(Collectors.toMap(
                                record -> record.get("deptID").asString(),
                                record -> record.get("quantity").asLong()
                        ));
            });
        }
    }

    /**
     * 14. Tổng số sinh viên đăng ký học của mỗi khoa, kết quả sắp xếp theo mã khoa
     * ORDER BY d.deptID
     */
    public Map<String, Long> countStudentsInEachDeptOrder() {
        String query = "MATCH (s:Student)-[:ENROLLED]->(c:Course)-[:BELONGS_TO]->(d:Department) " +
                "RETURN d.deptID as deptID, count(s) as quantity " +
                "ORDER BY d.deptID";
        try (Session session = driver.session(sessionConfig)) {
            return session.readTransaction(tx -> {
                Result result = tx.run(query);
                if(!result.hasNext()){
                    return null;
                }
                return result.list().stream()
                        .collect(Collectors.toMap(
                                record -> record.get("deptID").asString(),
                                record -> record.get("quantity").asLong(),
                                (oldValue, newValue) -> oldValue, // In case of duplicate keys, keep the old value
                                TreeMap::new // Use a TreeMap to keep the keys sorted
                        ));
            });
        }
    }

    /**
     * Tổng số sinh viên đăng ký học của mỗi khoa, kết quả sắp xếp theo số sinh viên
     * ORDER BY quantity DESC
     * @return
     *
     */
    public Map<String, Long> countStudentsInEachDeptOrderByQuantity() {
        String query = "MATCH (s:Student)-[:ENROLLED]->(c:Course)-[:BELONGS_TO]->(d:Department) " +
                "RETURN d.deptID as deptID, count(s) as quantity";
        try (Session session = driver.session(sessionConfig)) {
            return session.readTransaction(tx -> {
                Result result = tx.run(query);
                if(!result.hasNext()){
                    return null;
                }
                List<Map.Entry<String, Long>> sortedEntries = result.list().stream()
                        .map(record -> Map.entry(record.get("deptID").asString(), record.get("quantity").asLong()))
                        .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                        .collect(Collectors.toList());
                return sortedEntries.stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (oldValue, newValue) -> oldValue, // In case of duplicate keys, keep the old value
                                LinkedHashMap::new // Use a LinkedHashMap to maintain the order of entries
                        ));
            });
        }
    }

    /**
     * 16. Liệt kê danh sách tên của các trưởng khoa mà các khoa này không có sinh viên đăng ký học
     * MATCH (d:Department) WHERE NOT (d)<-[:BELONGS_TO]-(:Course)<-[:ENROLLED]-(:Student) RETURN d.dean
     */
    public List<String> getDeansOfDeptsWithNoStudents() {
        String query = "MATCH (d:Department) WHERE NOT (d)<-[:BELONGS_TO]-(:Course)<-[:ENROLLED]-(:Student) RETURN d.dean";
        try (Session session = driver.session()) {
            return session.readTransaction(tx -> {
                Result result = tx.run(query);
                if(!result.hasNext()){
                    return null;
                }
                return result.list(record -> record.get("d.dean").asString());
            });
        }
    }

    /**
     * . Danh sách khoa có số sinh viên đăng ký học nhiều nhất
     * "MATCH (s:Student)-[:ENROLLED]->(c:Course)-[:BELONGS_TO]->(d:Department) " +
     *  "WITH d.deptID as deptID, count(s) as quantity " +
     *   "ORDER BY quantity DESC " +
     *     "WITH collect({deptID: deptID, quantity: quantity}) as counts " +
     *      "RETURN [x IN counts WHERE x.quantity = counts[0].quantity] as departments";
     */
    public List<Map<String, Object>> getDeptWithMostStudents() {
        String query = "MATCH (s:Student)-[:ENROLLED]->(c:Course)-[:BELONGS_TO]->(d:Department) " +
                "WITH d.deptID as deptID, count(s) as quantity " +
                "ORDER BY quantity DESC " +
                "WITH collect({deptID: deptID, quantity: quantity}) as counts " +
                "RETURN [x IN counts WHERE x.quantity = counts[0].quantity] as departments";
        try (Session session = driver.session()) {
            return session.readTransaction(tx -> {
                Result result = tx.run(query);
                if(!result.hasNext()){
                    return null;
                }
                return result.single().get("departments").asList(record -> record.asMap());
            });
        }
    }
    public void closed() {
        driver.close();
    }
}
