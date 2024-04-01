package entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "OnlineCourse")
public class OnlineCourse {
    private String URL;
    @Id
    @OneToOne
    @JoinColumn(name = "CourseID")
    private Course CourseID;
}
