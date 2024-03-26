package dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestCustomer {
    private CustomerDao customerDao;
    @BeforeAll
    public void setUp() {
        customerDao = new CustomerDao();
    }
    @Test
    void ds() {
        System.out.println(customerDao.listCustomersWithTheNumberOfOrders());
    }
    @AfterAll
    public void tearDown() {
        customerDao.close();
    }
}
