package util;

import java.util.Map;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.types.Node;

import com.google.gson.Gson;

public class AppUtils {
	

	private static final Gson GSON = new Gson();

	public static Driver initDriver() {
		return GraphDatabase.driver("neo4j://localhost:7687", 
               AuthTokens.basic("neo4j", "12345678"));
	}

	public static <T> T convert(Node node, Class<T> clazz) {
		Map<String, Object> properties = node.asMap();
//		System.out.println(properties);
		
		String json = GSON.toJson(properties);
		
//		System.out.println(json);
		
		return GSON.fromJson(json, clazz);
	}

	
}
