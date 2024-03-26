/**
 * @ (#) AppUtils.java      3/20/2024
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
 * @date: 3/20/2024
 */
public class AppUtils {
    public static Driver getDriver() {
        String url = "neo4j://localhost:7687";
        String username = "neo4j";
        String password = "12345678";
        return GraphDatabase.driver(url, AuthTokens.basic(username, password));
    }

    public static <T> T nodeToPOJO(Node node, Class<T> clazz) {
        Gson gson = new Gson();
        Map<String, Object> map = node.asMap();
        String json = gson.toJson(map);
        return gson.fromJson(json, clazz);
    }
 }
