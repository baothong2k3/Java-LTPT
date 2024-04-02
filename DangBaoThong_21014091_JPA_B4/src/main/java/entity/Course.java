package entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Course")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c"),
        @NamedQuery(name = "Course.findByID", query = "SELECT c FROM Course c WHERE c.courseID = :id")
})
public abstract class Course {
    @Id
    @Column(name = "CourseID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int courseID;

    @Column(name = "Credits")
    protected int credits;

    @Column(name = "Title")
    protected String title;

    @ManyToOne
    @JoinColumn(name = "DepartmentID")
    protected Department department;

    public Course(int credits, String title) {
        this.credits = credits;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseID=" + courseID +
                ", credits=" + credits +
                ", title='" + title + '\'' +
                '}';
    }
}
