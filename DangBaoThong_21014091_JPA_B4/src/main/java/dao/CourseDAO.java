/*
 * @ (#) CourseDAO.java    1.0    02/04/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package dao;/*
 * @description:
 * @author: Bao Thong
 * @date: 02/04/2024
 * @version: 1.0
 */

import entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class CourseDAO {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_ORM_Student MSSQL");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    /**
     * @description: List all courses
     * @return: List<Course>
     */
    public List<Course> listAllCourses() {
        String query = "select c from Course c";
        List<Course> courses = null;
        try {
            tx.begin();
            courses = em.createQuery(query, Course.class).getResultList();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
        return courses;

    }

    /**
     * List of courses by credit
     * return List<Course>
     */
    public List<Course> listOfCourseByCredit(int credit) {
        String query = "select c from Course c where c.credits = :credit";
        List<Course> courses = null;
        try {
            tx.begin();
            courses = em.createQuery(query, Course.class).setParameter("credit", credit).getResultList();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
        return courses;
    }
    public void close() {
        em.close();
        emf.close();
    }
}
