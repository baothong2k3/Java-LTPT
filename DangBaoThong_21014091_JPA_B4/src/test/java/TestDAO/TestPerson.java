/*
 * @ (#) TestPerson.java    1.0    02/04/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package TestDAO;/*
 * @description:
 * @author: Bao Thong
 * @date: 02/04/2024
 * @version: 1.0
 */

import dao.PersonDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPerson {
    private PersonDAO personDAO;

    @BeforeEach
    void setUp() {
        personDAO = new PersonDAO();
    }

    @Test
    void testAddPerson() {
        boolean result = personDAO.addPerson("Dang", "Thong");
        assertTrue(result, "The returned value should be true");
    }
    @Test
    void testAddPersonInstructor() {
        boolean result = personDAO.addInstructor("Dang", "Thong", LocalDateTime.now());
        assertTrue(result, "The returned value should be true");
    }
    @Test
    void testDeletePerson() {
        boolean result = personDAO.deletePerson(36);
        assertTrue(result, "The returned value should be true");
    }
    @Test
    void testUpdatePerson() {
        boolean result = personDAO.updatePerson(37, "Bao Thong");
        assertTrue(result, "The returned value should be true");
    }
    @AfterEach
    void tearDown() {
        personDAO.close();
        personDAO = null;
    }
}
