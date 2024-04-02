package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
//@ToString
@Entity
@Table(name = "OnsiteCourse")
public class OnsiteCourse extends Course{
    @Column(name = "Days")
   private String days;
    @Column(name = "Location")
    private String location;
    @Column(name = "Time")
    private LocalDateTime time;

    public OnsiteCourse(String title, int credits, String days, String location, LocalDateTime time) {
        super(credits, title);
        this.days = days;
        this.location = location;
        this.time = time;
    }

    @Override
    public String toString() {
        return super.toString() + "OnsiteCourse{" +
                "days='" + days + '\'' +
                ", location='" + location + '\'' +
                ", time=" + time +
                '}';
    }
}
