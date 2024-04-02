package entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "StudentGrade")
public class StudentGrade {
    @ManyToOne
    @JoinColumn(name = "CourseID")
    private Course course;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int enrollmentId;
    @Column(name = "Grade")
    private double grade;

    @ManyToOne
    @JoinColumn(name = "StudentID")
    private Student studentId;

    public StudentGrade(Course course, double grade, Student student) {
        this.course = course;
        this.grade = grade;
        this.studentId = student;
    }
}
