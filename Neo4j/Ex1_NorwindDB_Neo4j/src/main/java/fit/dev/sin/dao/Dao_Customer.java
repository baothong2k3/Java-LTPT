/**
 * @ (#) Dao_Customer.java      3/16/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package fit.dev.sin.dao;

import fit.dev.sin.entity.Customer;
import fit.dev.sin.util.App_Utils;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/16/2024
 */
public class Dao_Customer {
    private Driver driver;

    public Dao_Customer() {
        driver = App_Utils.getDriver();
    }

    /**
     * 4. Thống kê số khách hàng theo từng thành phố (sắp xếp theo số khách hàng / theo city).
     * @return
     */
    public Map<String, Integer> getNumberCustomerByCity() {
        String query = "match (c:Customer) return c.city, count(*) order by c.city;";

        try(Session session = driver.session()){
            return session.executeRead(tx -> {
                Result rs = tx.run(query);
                if(!rs.hasNext()) {
                    return null;
                }
//                Map<String, Integer> map = new LinkedHashMap<>();
//                rs.stream().forEach(record -> {
//                    String node = record.get("c.city").asString();
//                    int count = record.get("count(*)").asInt();
//                    map.put(node, count);
//                });
//                return map;
                return rs.stream()
                        .collect(Collectors.toMap(
                                // key
                           record -> record.get("c.city").asString(),
                                // value
                           record -> record.get("count(*)").asInt(),
                                (v1, v2) -> v1,
                                ()-> new LinkedHashMap<>()
                        ));
            });
        }
    }

    public Map<Customer, Integer> getOrdersByCustomers() {
        String query = "match(c:Customer)-[:PURCHASED]->(o: Order) \n" +
                "return c,count(o.orderID);";

        try(Session session = driver.session()){
            return session.executeRead(tx -> {
               Result rs = tx.run(query);
               if(!rs.hasNext()) {
                   return null;
               }
               Map<Customer, Integer> map = new HashMap<>();
               rs.stream().forEach(record -> {
                  Node node = record.get("c").asNode();
                  Customer cs = App_Utils.nodeToPoJO(node, Customer.class);
                  int sum = record.get("count(o.orderID)").asInt();
                  map.put(cs, sum);
               });
               return map;
            });
        }
    }



    public void close() {
        driver.close();
    }

}
