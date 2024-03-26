/**
 * @ (#) DaoOrder.java      3/17/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package fit.dev.sin.dao;

import fit.dev.sin.entity.Customer;
import fit.dev.sin.util.App_Utils;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/17/2024
 */
public class Dao_Order {
    private final Driver driver;

    public Dao_Order() {
        driver = App_Utils.getDriver();
    }

    /**
     * 5. Tính tổng tiền của đơn hàng khi biết mã số đơn hàng.
     * @param id
     * @return
     */
    public double getSumOrderByID(String id) {
        String query = "match (o: Order)-[r:ORDERS]->(p:Product) \n" +
                "where o.orderID = $id \n" +
                "return sum(toFloat(r.unitPrice)*r.quantity) as sumPrice;";

        Map<String, Object> params = Map.of("id", id);

        try (Session session = driver.session()) {
            return session.executeRead(tx -> {
                Result rs = tx.run(query, params);
                if (!rs.hasNext()) {
                    return 0.0;
                }
                Record rc = rs.next();
                return rc.get("sumPrice").asNumber().doubleValue();
            });
        }
    }

    /**
     * 9. Tính tổng tiền của tất cả các hóa đơn trong một ngày nào đó.
     * @param order
     * @return
     */
    public double sumPriceOrderByDate(LocalDate order) {
        String query = "match (o: Order)-[r:ORDERS]->(p:Product) \n" +
                "where date(substring(o.orderDate,0,10)) = date($date) \n" +
                "return sum(toFloat(r.unitPrice)*r.quantity) as sumPrice;";

        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Map<String, Object> params = Map.of("date", dft.format(order));

        try (Session session = driver.session()) {
            return session.executeRead(tx -> {
                Result rs = tx.run(query, params);
                if (!rs.hasNext()) {
                    return 0.0;
                }
                Record rc = rs.next();
                return rc.get("sumPrice").asNumber().doubleValue();
            });
        }

    }

    /**
     * 6. Đếm số đơn hàng của từng khách hàng.
     * @return
     */
    public Map<Customer, Integer> getOrdersByCustomers() {
        String query = "match (p:Customer)-[:PURCHASED]->(o:Order) return p, count(o) as sumOrder;";
        try (Session session = driver.session()) {
            return session.executeRead(tx -> {
                Result rs = tx.run(query);
                if (!rs.hasNext()) {
                    return null;
                }
                Map<Customer, Integer> map = new LinkedHashMap<>();
                rs.stream().forEach(record -> {
                    Node node = record.get("p").asNode();
                    Customer customer = App_Utils.nodeToPoJO(node, Customer.class);
                    int sum = record.get("sumOrder").asInt();
                    map.put(customer, sum);
                });
                return map;
            });
        }
    }


    /**
     * 10. Thống kê tổng tiền hóa đơn theo tháng / năm.
     * @return
     */
    public Map<String, Double> statisticOrderByMonthAndYear() {
        String query = "match (o:Order)-[r:ORDERS]->(p:Product)\n" +
                "return date(substring(o.orderDate,0,10)).month as  month, date(substring(o.orderDate,0,10)).year as year,\n" +
                "  sum(toFloat(r.unitPrice)* r.quantity) as sumPrice\n" +
                "order by year, month;";
        try (Session session = driver.session()) {
            return session.executeRead(tx -> {
                Result rs = tx.run(query);
                if (!rs.hasNext()) {
                    return null;
                }
//                Map<String, Double> map = new LinkedHashMap<>();
//                rs.stream().forEach(record -> {
//                    int month = record.get("month").asInt();
//                    int year = record.get("year").asInt();
//                    String monthYear = month + "/" + year;
//
//                    double sum = record.get("sumPrice").asNumber().doubleValue();
//                    map.put(monthYear, sum);
//                });
//                return map;
                return rs.stream()
                        .collect(Collectors.toMap(
                           record -> {
                                 int month = record.get("month").asInt();
                                 int year = record.get("year").asInt();
                                 return month + "/" + year;
                            },
                                record -> record.get("sumPrice").asNumber().doubleValue(),
                                (v1, v2) -> v1,
                                ()-> new LinkedHashMap<>()
                        ));

            });
        }
    }


    public void close() {
        driver.close();
    }
}
