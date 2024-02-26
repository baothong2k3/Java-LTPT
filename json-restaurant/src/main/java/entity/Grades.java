package entity;

import java.util.Date;

public class Grades {
private Date date;
private String grade;
private int score;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public Grades() {
    }

    public Grades(Date date, String grade, int score) {
        this.date = date;
        this.grade = grade;
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
