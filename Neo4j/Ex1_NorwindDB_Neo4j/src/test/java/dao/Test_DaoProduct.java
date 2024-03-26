/**
 * @ (#) Test_DaoProduct.java      3/16/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package dao;

import fit.dev.sin.dao.Dao_Category;
import fit.dev.sin.dao.Dao_Product;
import fit.dev.sin.dao.Dao_Supplier;
import fit.dev.sin.entity.Category;
import fit.dev.sin.entity.Product;
import fit.dev.sin.entity.Supplier;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/16/2024
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test_DaoProduct {

    private Dao_Product dao_product;
    private Dao_Supplier dao_supplier2 = new Dao_Supplier();
    private Dao_Category dao_category2 = new Dao_Category();

    @BeforeAll
    public void setUp() {
        dao_product = new Dao_Product();
    }

     @Test
    public void testAddProduct() {
         Supplier supplier = dao_supplier2.findSupplierById("2");
         Category category = dao_category2.findCategoryById("1");

         Product product = new Product("P123", "Sinh", supplier, category, "12", 12, 12, 12, 12, false);
         Product product2 = dao_product.addProduct(product);

         Assertions.assertNotNull(product2);
         System.out.println(product2);
    }

    @Test
    public void testGetProductsByNameSupplier() {
        String companyName = "Tokyo Traders";
       List<Product> products = dao_product.getProductsByNameSupplier(companyName);
        Assertions.assertEquals(3, products.size());
    }

    @Test
    public void testGetProductsByMaxPrice() {
        List<Product> products = dao_product.getProductsByMaxPrice();
        Assertions.assertNotNull(products);
        products.forEach(product -> {
            Assertions.assertEquals(263.5, product.getUnitPrice());
        });
    }

    @Test
    public void testGetTotalProduct() {
        Map<String,Integer> total = dao_product.getTotalProduct();
        Assertions.assertEquals(1057, total.get("Chang"));

    }

    @Test
    public void testSearchProductByName() {
        List<Product> products = dao_product.searchProductByName("Chef");
        Assertions.assertNotNull(products);
        products.forEach(product -> {
            System.out.println(product);
        });
    }
    @AfterAll
    public void tearDown() {
        dao_product.close();
    }
}
