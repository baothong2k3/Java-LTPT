/*
 * @ (#) PersonDAO.java    1.0    02/04/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package dao;/*
 * @description:
 * @author: Bao Thong
 * @date: 02/04/2024
 * @version: 1.0
 */

import entity.Instructor;
import entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.List;

public class PersonDAO {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_ORM_Student MSSQL");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    /**
     * @description: List all persons
     * @return: List<Person>
     */
    public List<Person> listAllPersons() {
        String query = "select p from Person p";
        List<Person> persons = null;
        try {
            tx.begin();
            persons = em.createQuery(query, Person.class).getResultList();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
        return persons;
    }

    /**
     * Add person
     * firstname = "Dang"
     * lastname = "Thong"
     * return boolean
     */
    public boolean addPerson(String firstname, String lastname) {
        Person person = new Person(firstname, lastname);
        try {
            tx.begin();
            em.persist(person);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return false;
        } finally {
            em.close();
            emf.close();
        }
    }

    /**
     * Add Instructor
     * firstname = "Dang"
     * lastname = "Thong"
     * hireDate = LocalDateTime.now()
     * hireDate in Instructor, extend Person
     *
     * @return boolean
     */
    public boolean addInstructor(String firstname, String lastname, LocalDateTime hireDate) {
        Instructor instructor = new Instructor(firstname, lastname, hireDate);
        try {
            tx.begin();
            em.persist(instructor);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return false;
        } finally {
            em.close();
            emf.close();
        }
    }

    /**
     * Delete person by personId
     *
     * @param personId
     * @return boolean
     */
    public boolean deletePerson(int personId) {
        Person person = em.find(Person.class, personId);
        if (person == null) {
            return false;
        }
        try {
            tx.begin();
            em.remove(person);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return false;
        } finally {
            em.close();
            emf.close();
        }
    }

    /**
     * Update Lastname by personId
     * @param personId
     * @return boolean
     */
    public boolean updatePerson(int personId, String lastname) {
        Person person = em.find(Person.class, personId);
        if (person == null) {
            System.out.println("No person found with id: " + personId);
            return false;
        }
        try {
            tx.begin();
            person.setLastName(lastname);
            em.merge(person);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return false;
        } finally {
            em.close();
            emf.close();
        }
    }


    public void close() {
        em.close();
        emf.close();
    }
}
