package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import entity.Category;

@TestInstance(Lifecycle.PER_CLASS)
public class CategoryDaoTest {
	private CategoryDao categoryDao;
	
	@BeforeAll
	void setup() {
		categoryDao = new CategoryDao();
	}
	
	@Test
	void testFindOne() {
		Category c = categoryDao.findOne("1");
		System.out.println(c);
		assertNotNull(c);
		assertEquals("Beverages", c.getName());
		assertEquals(true, c.getDescription().contains("Soft drinks"));
	}
	
	@AfterAll
	void teardown() {
		categoryDao.close();
		categoryDao = null;
	}
}
