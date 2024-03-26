/**
 * @ (#) Test_DaoOrder.java      3/17/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package dao;

import fit.dev.sin.dao.Dao_Order;
import fit.dev.sin.entity.Customer;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.Map;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/17/2024
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test_DaoOrder {
    private Dao_Order daoOrder;

    @BeforeAll
    public void setup() {
        daoOrder = new Dao_Order();
    }

    @Test
    public void testGetSumOrderByID() {
        double sum = daoOrder.getSumOrderByID("10252");
        Assertions.assertEquals(117.5, sum);
    }

    @Test
    public void testSumPriceOrderByDate() {
        double sum = daoOrder.sumPriceOrderByDate(LocalDate.of(1996, 7, 9));
        Assertions.assertEquals(3730.0, sum);
        System.out.println(sum);
    }

    @Test
    public void testGetOrderByCustomer() {
        Map<Customer, Integer> map = daoOrder.getOrdersByCustomers();
        Assertions.assertNotNull(map);
        map.forEach((k, v) -> {
            System.out.println(k + "\n So luong don hang: " + v);
        });
    }

    @Test
    public void testStatisticOrderByMonthAndYear() {
        Map<String, Double> map = daoOrder.statisticOrderByMonthAndYear();
        Assertions.assertNotNull(map);
        map.forEach((k, v) -> {
            System.out.println(k + " : " + v);
        });
    }

    @AfterAll
    public void tearDown () {
        daoOrder.close();
    }

}
