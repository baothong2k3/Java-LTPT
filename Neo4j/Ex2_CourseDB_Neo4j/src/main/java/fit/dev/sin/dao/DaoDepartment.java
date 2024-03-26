/**
 * @ (#) DaoDepartment.java      3/20/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package fit.dev.sin.dao;

import fit.dev.sin.entities.Department;
import fit.dev.sin.util.AppUtils;
import lombok.NoArgsConstructor;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.SessionConfig;
import org.neo4j.driver.types.Node;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/20/2024
 */
@NoArgsConstructor
public class DaoDepartment {
    private Driver driver;
    private SessionConfig sessionConfig;
    public DaoDepartment(Driver driver, String dbName) {
        this.driver = driver;
        sessionConfig = SessionConfig.builder().withDatabase(dbName).build();
    }

    /**
     * Use to find department by id
     * @param deptID
     * @return
     */
    public Department findDepartmentByID(String deptID) {
        String query = "match (d: Department {deptID: $id}) return d;";
        try (Session session = driver.session(sessionConfig)) {
            return session.executeRead(tx-> {
                Result rs = tx.run(query, Map.of("id", deptID));
                if (!rs.hasNext()) {
                    return null;
                }
                Node node = rs.single().get("d").asNode();
                return AppUtils.nodeToPOJO(node,Department.class);
            });
        }
    }

    /**
     * 4. Cập nhật name = “Mathematics” cho department_id = “Math”
     * @param name
     * @param id
     * @return
     * match (d:Department{deptID: $id}) set d.name = $name return d;
     */
    public Department updateDepartmentByName(String name, String id) {
        String query = "match (d:Department{deptID: $id}) set d.name = $name return d;";
        Map<String, Object> map = Map.of("id", id, "name", name);

        try (Session session = driver.session(sessionConfig)) {
            return session.executeWrite(tx-> {
                Result rs = tx.run(query, map);
                if (!rs.hasNext()) {
                    return null;
                }
                Node node = rs.single().get("d").asNode();
                return AppUtils.nodeToPOJO(node, Department.class);
            });
        }
    }

    /**
     * 9. Liệt kê tên của tất cả các trưởng khoa
     * @return
     * match (d:Department) return d.dean;
     */
    public List<String> getAllNameDean() {
        String query = "match (d:Department) return d.dean;";
        try(Session session = driver.session(sessionConfig)) {
            return session.executeRead(tx -> {
                Result rs = tx.run(query);
                if (!rs.hasNext()) {
                    return null;
                }
                return rs.stream()
                        .map(record -> record.get("d.dean").asString())
                        .toList();
            });
        }
    }

    /**
     * 10. Tìm tên của trưởng khoa CS
     * @param deptID
     * @return
     * match (d:Department {deptID: $deptID}) return d.dean;
     */
    public String getDeanByDeptID(String deptID) {
        String query = "match (d:Department {deptID: $deptID}) return d.dean;";
        Map<String, Object> params = Map.of("deptID", deptID);

        try (Session session  =driver.session(sessionConfig)) {
            return session.executeRead(tx->{
                Result rs = tx.run(query, params);
                if (!rs.hasNext()) {
                    return null;
                }
                return rs.single().get("d.dean").asString();
            });
        }
    }

    /**
     * 13. Tổng số sinh viên đăng ký học của mỗi khoa
     * @return
     * match (s:Student)-[:ENROLLED]->(c:Course)-[:BELONGS_TO]->(d:Department)return d.deptID as deptID, count(s) as quantity;
     */
    public Map<String, Integer> sumQuantitySVOfDept() {
        String query = "match (s:Student)-[:ENROLLED]->(c:Course)-[:BELONGS_TO]->(d:Department)return d.deptID as deptID, count(s) as quantity;";
        try (Session session = driver.session(sessionConfig)) {
            return session.executeRead(tx-> {
                Result rs = tx.run(query);
                if (!rs.hasNext()) {
                    return null;
                }
//                Map<String, Integer> map = new HashMap<>();
//                rs.stream().forEach(record -> {
//                    String deptID = record.get("deptID").asString();
//                    int quantity = record.get("quantity").asInt();
//                    String name = findDepartmentByID(deptID).getName();
//                    map.put(name, quantity);
//                });
//                return map;

                return rs.stream()
                        .collect(Collectors.toMap(
                                // key
                           record -> {
                                 String deptID = record.get("deptID").asString();
                                 return findDepartmentByID(deptID).getName();
                                 },
                                // value
                           record -> record.get("quantity").asInt(),
                                (v1, v2) -> v1,
                                () -> new LinkedHashMap<>()
                        ));
            });
        }
    }

    /**
     * 14. Tổng số sinh viên đăng ký học của mỗi khoa và sắp xếp theo deptID
     * @return
     * match (s:Student)-[:ENROLLED]->(c:Course)-[:BELONGS_TO]->(d:Department)return d.deptID as deptID, count(s) as quantity order by deptID asc;
     */
    public Map<String, Integer> sumQuantitySVOfDeptSortDeptID() {
        String query = "match (s:Student)-[:ENROLLED]->(c:Course)-[:BELONGS_TO]->(d:Department)return d.deptID as deptID, count(s) as quantity order by deptID asc;";
        try (Session session = driver.session(sessionConfig)) {
            return session.executeRead(tx-> {
               Result rs = tx.run(query);
               if(!rs.hasNext()) {
                   return null;
               }
                Map<String, Integer> map = new LinkedHashMap<>();
                rs.stream().forEach(record -> {
                    String deptID = record.get("deptID").asString();
                    int quantity = record.get("quantity").asInt();
                    String name = findDepartmentByID(deptID).getName();
                    map.put(name, quantity);
                });
                return map;
            });
        }
    }

    /**
     * 16. Liệt kê danh sách tên của các trưởng khoa mà các khoa này không có sinh viên đăng ký học
     * @return
     * match (d:Department) where not (d)<-[:BELONGS_TO]-(:Course)<-[:ENROLLED]-(:Student) return d.dean;
     */
    public List<String> listOfNameDeanNoSt() {
        String query = "match (d:Department) " +
                "where not (d)<-[:BELONGS_TO]-(:Course)<-[:ENROLLED]-(:Student)\n" +
                "return d.dean;";
        try(Session session = driver.session(sessionConfig)) {
            return session.executeRead(tx -> {
               Result rs = tx.run(query);
               if(!rs.hasNext()) {
                   return null;
               }
               return rs.stream()
                       .map(record -> record.get("d.dean").asString()).toList();
            });
        }
    }

    /**
     * 17. Danh sách khoa có số sinh viên đăng ký học nhiều nhất
     * @return
     * Cách 1:
     * match (s:Student)-[:ENROLLED]->(c:Course)-[:BELONGS_TO]->(d:Department)
     *                with d.deptID as deptID, count(s) as quantity_student
     *                with max(quantity_student) as maxSLSV
     *                match (s:Student)-[:ENROLLED]->(c:Course)-[:BELONGS_TO]->(d:Department)
     *                with d.deptID as deptID, count(s) as quantity_student, maxSLSV
     *                where quantity_student = maxSLSV
     *                return deptID, quantity_student;
     *
     * Cách 2:
     * match (s:Student)-[:ENROLLED]->(c:Course)-[:BELONGS_TO]->(d:Department)
     * with d.deptID as deptID, count(s) as quantity_student
     * with max(quantity_student) as maxSLSV, collect({dept_id:deptID, quantity:quantity_student}) as temp
     * unwind temp as t
     * with maxSLSV, t
     * where t.quantity = maxSLSV
     * return t.dept_id,t.quantity
     */
    public Map<Department, Integer> listOfDeptStdMax() {
        String query = "match (s:Student)-[:ENROLLED]->(c:Course)-[:BELONGS_TO]->(d:Department)\n" +
                "with d.deptID as deptID, count(s) as quantity_student\n" +
                "with max(quantity_student) as maxSLSV\n" +
                "match (s:Student)-[:ENROLLED]->(c:Course)-[:BELONGS_TO]->(d:Department)\n" +
                "with d.deptID as deptID, count(s) as quantity_student, maxSLSV \n" +
                "where quantity_student = maxSLSV\n" +
                "return deptID, quantity_student;";

        try (Session session = driver.session(sessionConfig)) {
            return session.executeRead(tx-> {
               Result rs = tx.run(query);
               if(!rs.hasNext()) {
                   return null;
               }
//               Map<Department, Integer> map = new HashMap<>();
//               rs.stream().forEach(record -> {
//                  Department dept = findDepartmentByID(record.get("deptID").asString());
//                  int quantity = record.get("quantity_student").asInt();
//                  map.put(dept, quantity);
//               });
//               return map;
                return rs.stream()
                        .collect(Collectors.toMap(
                                // key
                           record -> findDepartmentByID(record.get("deptID").asString()),
                                // value
                           record -> record.get("quantity_student").asInt(),
                                (v1, v2) -> v1,
                                () -> new LinkedHashMap<>()
                        ));
            });
        }
    }

    public void close() {
        driver.close();
    }
}
