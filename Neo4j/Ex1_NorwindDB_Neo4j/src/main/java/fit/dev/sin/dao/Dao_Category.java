/**
 * @ (#) Dao_Category.java      3/19/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package fit.dev.sin.dao;

import fit.dev.sin.entity.Category;
import fit.dev.sin.util.App_Utils;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;

import java.util.Map;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/19/2024
 */
public class Dao_Category {
    private Driver driver;

    public Dao_Category() {
        driver = App_Utils.getDriver();
    }

    public Category findCategoryById(String id) {
        String query = "Match (c:Category {categoryID: $id}) return c";
        Map<String, Object> params = Map.of("id", id);
        try (Session session = driver.session()){
            return session.executeRead(tx -> {
                Result rs = tx.run(query,params);
                if(!rs.hasNext()) {
                    return null;
                }
                Node node = rs.next().get("c").asNode();
                return App_Utils.nodeToPoJO(node, Category.class);
            });
        }
    }
    public void close() {
        driver.close();
    }
}
