/**
 * @ (#) Test_DaoSupplier.java      3/16/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package dao;

import fit.dev.sin.dao.Dao_Supplier;
import fit.dev.sin.entity.Supplier;
import org.junit.jupiter.api.*;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/16/2024
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test_DaoSupplier {
    private Dao_Supplier dao_supplier;
    @BeforeAll
    public void setUp() {
        dao_supplier = new Dao_Supplier();
    }

    @Test
    public void testCreateSupplier() {
        Supplier s = new Supplier("Sup_01", "Choes", "TSinh", "LienHe", "Ha Huy Giap", "TPHCM");
        dao_supplier.createSupplier(s);
        Supplier s1 = dao_supplier.findSupplierById("Sup_01");
        Assertions.assertNotNull(s1);
        Assertions.assertEquals("Sup_01", s1.getSupplierID());
        Assertions.assertEquals("Choes", s1.getCompanyName());
        Assertions.assertEquals("TSinh", s1.getContactName());
        Assertions.assertEquals("LienHe", s1.getContactTitle());
        Assertions.assertEquals("Ha Huy Giap", s1.getAddress());
        Assertions.assertEquals("TPHCM", s1.getCity());
    }

    @Test
    public void testfindSupplierById() {
        Supplier s1 = dao_supplier.findSupplierById("Sup_01");
        Assertions.assertNotNull(s1);
        Assertions.assertEquals("Sup_01", s1.getSupplierID());
        Assertions.assertEquals(true, s1.getCompanyName().contains("Choes"));
        Assertions.assertEquals("TSinh", s1.getContactName());
        Assertions.assertEquals("LienHe", s1.getContactTitle());
        Assertions.assertEquals("Ha Huy Giap", s1.getAddress());
        Assertions.assertEquals("TPHCM", s1.getCity());
    }

    @Test
    public void testDeleteSupplier() {
        dao_supplier.deleteSupplier("S1");
        Supplier s1 = dao_supplier.findSupplierById("S1");
        Assertions.assertNull(s1);
    }


    @Test
    public  void testUpdateSupplier() {
        Supplier supplierFind = dao_supplier.findSupplierById("Sup_01");
        Assertions.assertNotNull(supplierFind);

        String contactName = "Tien Sinh";
        Supplier supplier = dao_supplier.updateSupplierForContactName(supplierFind.getSupplierID(), contactName);
        Assertions.assertNotNull(supplier);
        Assertions.assertEquals(contactName, supplier.getContactName());

     }
    @AfterAll
    public void tearDown() {
        dao_supplier.close();
    }
}
