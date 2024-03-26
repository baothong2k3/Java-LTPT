/**
 * @ (#) DaoCourse.java      3/20/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package fit.dev.sin.dao;

import fit.dev.sin.entities.Course;
import fit.dev.sin.util.AppUtils;
import lombok.NoArgsConstructor;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.SessionConfig;
import org.neo4j.driver.types.Node;

import java.util.List;
import java.util.Map;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/20/2024
 */
@NoArgsConstructor
public class DaoCourse {
    private Driver driver;
    private SessionConfig sessionConfig;
    private DaoDepartment daoDepartment;
    public DaoCourse(Driver driver, String dbName){
        this.driver = driver;
        sessionConfig = SessionConfig.builder().withDatabase(dbName).build();
        daoDepartment = new DaoDepartment(driver, dbName);
    }

    /**
     * 3. Tìm danh sách khóa học thuộc của một khoa nào đó khi biết mã khoa
     * @param deptId
     * @return list
     * match (c:Course)-[:BELONGS_TO]->(d:Department {deptID: $deptId}) return c;
     */
    public List<Course> listOfCourseByDeptID(String deptId) {
        String query = "match (c:Course)-[:BELONGS_TO]->(d:Department {deptID: $deptId}) return d.deptID, c;";
        Map<String, Object> map = Map.of("deptId", deptId);

        try(Session session = driver.session(sessionConfig)) {
            return session.executeRead(tx ->{
                Result rs = tx.run(query, map);
                if(!rs.hasNext()) {
                    return null;
                }

                return rs.stream()
                        .map(record -> {
                            Node node = record.get("c").asNode();
                            Course c =  AppUtils.nodeToPOJO(node, Course.class);
                            c.setDepartment(daoDepartment.findDepartmentByID(record.get("d.deptID").asString()));
                            return c;
                        }).toList();

            });
        }
    }

    /**
     * 6. Thêm khóa học vào khoa IE: IE202, Simulation, 3 hours.
     * @param deptID
     * @param courseAdd
     * @return course
     * match (d:Department {deptID: $deptID}) create (c:Course {courseID: $courseID, name: $name, hours : $hours})-[:BELONGS_TO]->(d) return c;
     */
    public Course createCourseByDept(String deptID, Course courseAdd) {
        String query = "match (d:Department {deptID: $deptID}) create (c:Course {courseID: $courseID, name: $name, hours : $hours})-[:BELONGS_TO]->(d) return c;";
        Map<String, Object> map = Map.of("deptID", deptID, "courseID", courseAdd.getId(), "name", courseAdd.getName(), "hours", courseAdd.getHours());

        try (Session session = driver.session(sessionConfig)) {
            return session.executeWrite(tx -> {
                Result rs = tx.run(query, map);
                if (!rs.hasNext()) {
                    return null;
                }
                Node node = rs.single().get("c").asNode();
                return AppUtils.nodeToPOJO(node, Course.class);
            });
        }
    }

    /**
     * 11. Liệt kê tất cả các khóa học của CS và IE
     * @param deptID
     * @return
     * match (c:Course)-[:BELONGS_TO]->(d:Department {deptID: $deptID}) return c;
     */
    public List<Course> listOfCourseByDept(String deptID) {
        String qeury = "match (c:Course)-[:BELONGS_TO]->(d:Department {deptID: $deptID}) return c;";
        Map<String, Object> map = Map.of("deptID", deptID);
        try (Session session = driver.session(sessionConfig)) {
            return session.executeRead(tx -> {
                Result rs = tx.run(qeury, map);
                if (!rs.hasNext()) {
                    return null;
                }
                return rs.stream()
                        .map(record -> {
                            Node node = record.get("c").asNode();
                            Course c = AppUtils.nodeToPOJO(node, Course.class);
                            c.setDepartment(daoDepartment.findDepartmentByID(deptID));
                            return c;
                        }).toList();
            });
        }
    }

    public void close(){
        driver.close();
    }
}
