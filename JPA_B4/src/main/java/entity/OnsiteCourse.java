package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "OnsiteCourse")
public class OnsiteCourse {
    private String location;
    private String days;
    private LocalDateTime time;
    @Id
    @OneToOne
    @JoinColumn(name = "CourseID")
    private Course CourseID;
}
