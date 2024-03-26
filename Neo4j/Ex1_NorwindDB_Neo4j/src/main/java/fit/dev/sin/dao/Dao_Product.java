/**
 * @ (#) Dao_Product.java      3/16/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package fit.dev.sin.dao;

import fit.dev.sin.entity.Category;
import fit.dev.sin.entity.Product;
import fit.dev.sin.entity.Supplier;
import fit.dev.sin.util.App_Utils;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/16/2024
 */
public class Dao_Product {
    private Driver driver;
    private Dao_Supplier dao_supplier;
    private Dao_Category dao_category;
    public Dao_Product() {
        driver = App_Utils.getDriver();
        dao_supplier = new Dao_Supplier();
        dao_category = new Dao_Category();
    }

    public Product addProduct(Product product) {
        String query = "CREATE (p: Product {productID: $productID, productName: $productName, quantityPerUnit : $quantityPerUnit,unitPrice: $unitPrice, unitsInStock: $unitsInStock, unitsOnOrder: $unitsOnOrder, reorderLevel: $reorderLevel, discontinued: $discontinued, supplierID: $supplierID, categoryID: $categoryID}) RETURN p;";
        Map<String, Object> map = Map.of(
                "productID", product.getProductID(),
                "productName", product.getProductName(),
                "unitPrice", product.getUnitPrice(),
                "quantityPerUnit", product.getQuantityPerUnit(),
                "unitsInStock", product.getUnitsInStock(),
                "unitsOnOrder", product.getUnitsOnOrder(),
                "reorderLevel", product.getReorderLevel(),
                "discontinued", product.isDiscontinued(),
                "supplierID", product.getSupplier().getSupplierID(),
                "categoryID", product.getCategory().getCategoryID()
        );

        try (Session sesion = driver.session()) {
            return sesion.executeWrite(tx -> {
                Product p_find = findProductById(product.getProductID());
                if (p_find != null) {
                    try {
                        throw new Exception("Product is existed");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                Result rs = tx.run(query, map);
                if (!rs.hasNext()) {
                    return null;
                }
                Node node = rs.single().get("p").asNode();
                Product p = App_Utils.nodeToPoJO(node, Product.class);
                p.setSupplier(dao_supplier.findSupplierById(node.get("supplierID").asString()));
                p.setCategory(dao_category.findCategoryById(node.get("categoryID").asString()));
                return p;
            });
        }
    }

    public Product findProductById(String id) {
        String query = "Match (p:Product {productID: $id}) return p";
        Map<String, Object> params = Map.of("id", id);
        try (Session session = driver.session()) {
            return session.executeRead(tx -> {
                Result rs = tx.run(query, params);
                if (!rs.hasNext()) {
                    return null;
                }
                Node node = rs.next().get("p").asNode();
                Product product = App_Utils.nodeToPoJO(node, Product.class);
                product.setSupplier(dao_supplier.findSupplierById(node.get("supplierID").asString()));
                product.setCategory(dao_category.findCategoryById(node.get("categoryID").asString()));
                return product;
            });
        }
    }

    /**
     *  Tìm các sản phẩm được cung cấp bởi một nhà cung cấp nào đó khi biết tên nhà cung cấp
     * @param nameSupplier
     * @return
     */
    public List<Product> getProductsByNameSupplier(String nameSupplier) {
        String query = "match(p:Product)<-[:SUPPLIES]-(s:Supplier{companyName: $companyName}) return p;";
        Map<String, Object> params = Map.of("companyName", nameSupplier);
        try (Session session = driver.session())
        {
            return session.executeRead(tx -> {
                Result rs = tx.run(query, params);
                if(!rs.hasNext()) {
                    return null;
                }

                return rs.stream()
                        .map(record -> {
                            Node node = record.get("p").asNode();
                            Product product = App_Utils.nodeToPoJO(node, Product.class);
                            product.setSupplier(dao_supplier.findSupplierById(node.get("supplierID").asString()));
                            product.setCategory(dao_category.findCategoryById(node.get("categoryID").asString()));
                            return product;
                        }).toList();
            });
        }
    }

    /**
     * 3. Tìm danh sách sản phẩm có giá cao nhất
     * @return
     */
    public List<Product> getProductsByMaxPrice() {
        String query = "match (p:Product) \n" +
                "with max(p.unitPrice) as maxPrice\n" +
                "match (p:Product)\n" +
                "where p.unitPrice = maxPrice\n" +
                "return p;";

        try (Session session = driver.session()) {
            return session.executeRead(tx -> {
                Result rs = tx.run(query);
                if (!rs.hasNext()) {
                    return null;
                }
                return rs.stream()
                        .map(record -> {
                            Node node = record.get("p").asNode();
                            Product product = App_Utils.nodeToPoJO(node, Product.class);
                            product.setSupplier(dao_supplier.findSupplierById(node.get("supplierID").asString()));
                            product.setCategory(dao_category.findCategoryById(node.get("categoryID").asString()));
                            return product;
                        }).toList();
            });
        }
    }

    /**
     * 7. Tính tổng số lượng của từng sản phẩm đã bán ra.
     * @return
     */
    public Map<String, Integer> getTotalProduct() {
        String query = "match (p:Product)<-[r:ORDERS]-(o:Order) return p.productName, sum(r.quantity) as sl;";
        try (Session session = driver.session()) {
            return session.executeRead(tx ->{
                Result rs = tx.run(query);
                if(!rs.hasNext()) {
                    return null;
                }
                Map<String, Integer> map = new java.util.LinkedHashMap<>();
                rs.stream().forEach(record -> {
                    String productName = record.get("p.productName").asString();
                    int count = record.get("sl").asInt();
                    map.put(productName, count);
                });
                return map;
            });
        }
    }

    /**
     * 8. Dùng text search để tìm kiếm sản phẩm theo tên sản phẩm.
     * @param name
     * @return
     *  create fulltext index index_nameProduct for (p:Product) on each [p.productName]
     */
    public List<Product> searchProductByName(String name) {
        String query  = "call db.index.fulltext.queryNodes(\"index_nameProduct\", $name) yield node return node;";
        Map<String,Object> params = Map.of("name", name);
        try(Session session = driver.session()) {
            return session.executeRead(tx -> {
               Result rs = tx.run(query, params);
               if(!rs.hasNext()) {
                     return null;
               }

               return rs.stream().map(record -> {
                   Node node = record.get("node").asNode();
                   Product p = App_Utils.nodeToPoJO(node, Product.class);
                   p.setCategory(dao_category.findCategoryById(node.get("categoryID").asString()));
                   p.setSupplier(dao_supplier.findSupplierById(node.get("supplierID").asString()));
                   return p;
               }).toList();

            });
        }
    }

    public void close() {
        driver.close();
    }


}
