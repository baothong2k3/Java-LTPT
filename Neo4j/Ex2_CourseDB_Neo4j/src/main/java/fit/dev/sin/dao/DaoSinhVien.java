/**
 * @ (#) DaoSinhVien.java      3/20/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package fit.dev.sin.dao;

import fit.dev.sin.entities.Student;
import fit.dev.sin.util.AppUtils;
import lombok.NoArgsConstructor;
import org.neo4j.driver.*;
import org.neo4j.driver.types.Node;

import java.util.List;
import java.util.Map;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/20/2024
 */
@NoArgsConstructor
public class DaoSinhVien {
    private Driver driver;

    private SessionConfig sessionConfig;
    public DaoSinhVien(Driver driver, String dbName) {
        this.driver = driver;
        sessionConfig = SessionConfig.builder().withDatabase(dbName).build();

    }

    /**
     * 1. Liệt kê danh sách n sinh viên
     * @param n
     * @return
     * match (s: Student) return s limit $n;
     */
    public List<Student> listOfStudent(int n) {
        String query = "match (s: Student) return s limit $n;";

        try (Session session = driver.session(sessionConfig)) {
             return session.executeRead(tx-> {
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
     * 2. Tìm sinh viên theo mã sinh viên
     * @param id
     * @return
     */
    public Student findStudentByID(String id) {
        String query = "match (s: Student {studentID: $id}) return s;";
        Map<String, Object> map = Map.of("id", id);

        try (Session session = driver.session(sessionConfig)) {
            return session.executeRead(tx-> {
               Result rs = tx.run(query, map);
                if (!rs.hasNext()) {
                     return null;
                }
                Node node = rs.single().get("s").asNode();
                return AppUtils.nodeToPOJO(node, Student.class);
            });
        }

    }

    /**
     * 12. Liệt kê danh sách các tên của các sinh viên đăng ký học khóa học CS101
     * @param id
     * @return
     * match (s: Student)-[:ENROLL]->(c:Course {courseID: $id}) return s.name;
     */
    public List<String> getListOfStudentByCouseID(String id) {
        String query = "match (s: Student)-[:ENROLL]->(c:Course {courseID: $id}) return s.name;";
        Map<String, Object> map = Map.of("id", id);
        try (Session session = driver.session(sessionConfig)) {
            return session.executeRead(tx-> {
                Result rs = tx.run(query, map);
                if (!rs.hasNext()) {
                    return null;
                }
                return rs.stream()
                        .map(record -> record.get("s.name").asString())
                        .toList();
            });
        }
    }

    /**
     * 18. Danh sách sinh viên có gpa >= 3.2, kết quả sắp xếp giảm dần theo gpa
     * @return
     * match (s:Student) where s.gpa >= 3.2 return s;
     */
    public List<Student> listOfStudentGpa() {
        String query = "match (s:Student) where s.gpa >= 3.2 return s;";
        try (Session session = driver.session(sessionConfig)) {
            return session.executeRead(tx -> {
               Result rs = tx.run(query);
               if(!rs.hasNext()) {
                   return null;
               }
               return rs.stream()
                       .map(record -> {
                           Node node = record.get("s").asNode();
                           return AppUtils.nodeToPOJO(node, Student.class);
                       }).toList();
            });
        }
    }


    public void close() {
        driver.close();
    }
}
