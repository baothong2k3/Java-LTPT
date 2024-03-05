package dao;

import java.util.Map;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;

import entity.Category;
import util.AppUtils;

public class CategoryDao {
	private Driver driver;
	
	public CategoryDao() {
		driver = AppUtils.initDriver();
	}
	
	public Category findOne(String id) {
		String query = "MATCH (n:Category) WHERE n.categoryID = $id RETURN n";
		Map<String, Object> map = Map.of("id", id);
		
		try(Session session = driver.session()){
			return session.executeRead(tx -> {
				Result result = tx.run(query, map);
				if(!result.hasNext())
					return null;
				 
				Record record = result.next();
				Node node = record.get("n").asNode();
				
//				String name = node.get("categoryName").asString();
//				String dest = node.get("description").asString();
				
//				return new Category(id, name, dest);
				
				Category c = AppUtils.convert(node, Category.class);
				
				return c;
				
			});
		}
	}
	
	public void close () {
		driver.close();
	}
}
