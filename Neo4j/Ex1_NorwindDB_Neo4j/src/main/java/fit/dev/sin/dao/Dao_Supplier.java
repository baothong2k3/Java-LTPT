/**
 * @ (#) Dao_Supplier.java      3/16/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package fit.dev.sin.dao;

import fit.dev.sin.entity.Supplier;
import fit.dev.sin.util.App_Utils;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;

import java.util.Map;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/16/2024
 */
public class Dao_Supplier {
    private Driver driver;

    public Dao_Supplier() {
        driver = App_Utils.getDriver();
    }

    public void createSupplier(Supplier supplier) {
        String query = "Create (s: Supplier{supplierID: $id, companyName: $companyName, contactName: $contact, contactTitle: $title, address: $address, city: $city})";
        Map<String, Object> params = Map.of("id", supplier.getSupplierID(),
                "companyName", supplier.getCompanyName(),
                "contact", supplier.getContactName(),
                "title", supplier.getContactTitle(),
                "address", supplier.getAddress(), "city", supplier.getCity());
        try (Session session = driver.session()) {
            session.executeWrite(tx -> {
                return tx.run(query, params).consume();
            });
        }
    }

    public Supplier findSupplierById(String id) {
        String query = "Match (s:Supplier {supplierID: $id}) return s";
        Map<String, Object> params = Map.of("id", id);
        try (Session session = driver.session()) {
            return session.executeRead(tx -> {
                Result result = tx.run(query, params);
                if (!result.hasNext()) {
                    return null;
                }
                Record record = result.next();
                Node node = record.get("s").asNode();
                return new Supplier(node.get("supplierID").asString(), node.get("companyName").asString(), node.get("contactName").asString(), node.get("contactTitle").asString(), node.get("address").asString(), node.get("city").asString());
            });
        }
    }

    public void deleteSupplier(String id) {
        String query = "Match (s:Supplier {supplierID: $id}) delete s";
        Map<String, Object> params = Map.of("id", id);
        try (Session session = driver.session()) {
            session.executeWrite(tx -> {
                return tx.run(query, params).consume();
            });
        }
    }

    public Supplier updateSupplierForContactName(String supplierID, String contactName) {
        String query = "Match (s: Supplier {supplierID: $id}) set s.contactName = $contact return s";
        Map<String, Object> params = Map.of("id", supplierID,
                "contact", contactName);

        try (Session session = driver.session()) {
            return session.executeWrite(tx -> {
                Result rs = tx.run(query, params);
                if(!rs.hasNext()) {
                    return null;
                }

                Record record = rs.next();
                Node node = record.get("s").asNode();
                return new Supplier(node.get("supplierID").asString(), node.get("companyName").asString(), node.get("contactName").asString(), node.get("contactTitle").asString(), node.get("address").asString(), node.get("city").asString());
            });
        }

    }

    public void close() {
        driver.close();
    }
}
