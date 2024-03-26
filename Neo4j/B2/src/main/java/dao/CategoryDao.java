package dao;

import entity.Category;
import utils.AppUtils;
import java.util.Map;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;

public class CategoryDao {
	private Driver driver;
	
	public CategoryDao() {
		driver = AppUtils.getDriver();
	}
	
	
	/**
	 * Create a new category
	 * @param category - Category object
	 */
	
	public void addCategory(Category category) {
		String query = "CREATE (n:Category {categoryID: $categoryID, categoryName: $categoryName, description: $description})";
	
		Map<String, Object> pars = AppUtils.getProperties(category);
		
		try (Session session = driver.session()) {
			session.executeWrite(tx -> {
				return tx.run(query, pars).consume();
			});
		}
	}
	
	/**
	 * Find one category by id
	 * @param id - Category id
	 * @return Category
	 */
	
	public Category findOne(String categoryID) {
		String query = "MATCH (n:Category) where n.categoryID= $id RETURN n";
		Map<String, Object> pars = Map.of("id", categoryID);
		
		try(Session session = driver.session()){
			return session.executeRead(tx -> {
				Result result = tx.run(query, pars);
				
				if (!result.hasNext()) 
                    return null;
				Record record = result.next();
				Node node = record.get("n").asNode();
				
				Category category = AppUtils.nodeToPOJO(node, Category.class);
				
				return category;
			});
		}
		
	}
	
	
	public void close() {
		driver.close();
	}
	
	public static void main(String[] args) {
		CategoryDao categoryDao = new CategoryDao();
		Category category = new Category("A124234", "sdfsd", "sdfsd");
		categoryDao.addCategory(category);
		categoryDao.close();
		System.out.println("Done!");
	}
}
