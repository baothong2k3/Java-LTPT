package dao;

import java.util.List;
import java.util.Map;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;

import entity.Product;
import util.AppUtils;

public class ProductDao {
	private Driver driver;

	public ProductDao() {
		this.driver = AppUtils.initDriver();
	}

	/**
	 * Find a product by its id
	 * 
	 * @param id - The id of the product
	 * @return The product with the given id
	 *
	 */
	public Product findProductById(String id) {
		String query = "MATCH (n:Product) where n.productID = $id  RETURN n";
		try (Session session = driver.session()) {
			return session.executeRead(tx -> {

				Result result = tx.run(query, Map.of("id", id));
				if (!result.hasNext())
					return null;

				Record record = result.next();
				Node node = record.get("n").asNode();

				return AppUtils.convert(node, Product.class);
			});
		}
	}

	public List<Product> listProductsByCategory(String name) {
		String query = "MATCH (p:Product)-[r:PART_OF]->(c:Category) where c.categoryName = $name RETURN p ";
		Map<String, Object> map = Map.of("name", name);
		try (Session session = driver.session()) {
			return session.executeRead(tx -> {

				Result result = tx.run(query, map);

				return result.stream() // Stream<Record>
						.map(record -> record.get("p").asNode())
						.map(node -> {
							return AppUtils.convert(node, Product.class);
						}).toList();

			});
		}

	}

	public void close() {
		driver.close();
	}
	
	
	public static void main(String[] args) {
		
		ProductDao productDao = new ProductDao();
		productDao.listProductsByCategory("Beverages").forEach(x -> System.out.println(x));
		
	}
}
