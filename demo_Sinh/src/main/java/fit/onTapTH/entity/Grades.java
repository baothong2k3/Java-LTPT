/**
 * @ (#) Grades.java      2/16/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package fit.onTapTH.entity;

import java.time.LocalDate;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 2/16/2024
 */
public class Grades {

    private LocalDate date;
    private String grade;
    private int score;

    public Grades() {
    }

    public Grades(LocalDate date, String grade, int score) {
        this.date = date;
        this.grade = grade;
        this.score = score;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Grades{" +
                "date=" + date +
                ", grade='" + grade + '\'' +
                ", score=" + score +
                '}';
    }
}
