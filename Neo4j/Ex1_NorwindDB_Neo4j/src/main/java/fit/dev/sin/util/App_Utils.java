/**
 * @ (#) App_Utils.java      3/16/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package fit.dev.sin.util;

import com.google.gson.Gson;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.types.Node;

import java.util.Map;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/16/2024
 */
public class App_Utils {
    private static final Gson GSON = new Gson();
    public static Driver getDriver() {
        String url = "neo4j://localhost:7687";
        String username = "neo4j";
        String password = "123456789";
        return GraphDatabase.driver(url, AuthTokens.basic(username, password));
    }

    public static <T> T nodeToPoJO(Node node, Class<T> clazz) {
        Map<String, Object> properties = node.asMap();
        String json = GSON.toJson(properties);
        return GSON.fromJson(json, clazz);
    }
}
