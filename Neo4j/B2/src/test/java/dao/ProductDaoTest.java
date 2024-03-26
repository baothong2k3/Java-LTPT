package dao;

import entity.Product;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.util.List;
import java.util.Map;

@TestInstance(Lifecycle.PER_CLASS)
public class ProductDaoTest {
	private ProductDao productDao;
	
	@BeforeAll
	void setup() {
		productDao = new ProductDao();
	}
	@Test
	void search(){
		List<Product> products = productDao.searchProductByName("Grandma's Boysenberry Spread");
		products.forEach(System.out::println);
	}
	@Test
	void listOrderByQuantity(){
		Map<String, Integer> products = productDao.listOfProductsWithTheTotalQuantityOfOrders();
		products.forEach((k, v) -> System.out.println(k + " : " + v));
	}
	@AfterAll
	void teardown() {
		productDao.close();
		productDao = null;
	}

}
