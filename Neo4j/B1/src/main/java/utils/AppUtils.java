package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Driver;
import org.neo4j.driver.types.Node;

import java.util.Map;


public class AppUtils<T> {
    private static final Gson GSON = new GsonBuilder().create();

    public static Driver getDriver() {
        return GraphDatabase.driver("neo4j://localhost:7687",
                AuthTokens.basic("neo4j", "12345678"));
    }

    public static <T> T nodeToPOJO(Node node, Class<T> clazz) {
        Map<String, Object> properties = node.asMap();
        String json = GSON.toJson(properties);
        T obj = GSON.fromJson(json, clazz);
        return obj;
    }
    public static <T> Map<String,Object> getProperties(T t){
        String json = GSON.toJson(t);
        Map<String,Object> map =GSON.fromJson(json, new TypeToken<Map<String,Object>>(){});
        return map;
    }

}
