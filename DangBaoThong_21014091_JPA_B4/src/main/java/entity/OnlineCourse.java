package entity;

import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.checker.units.qual.C;

@Getter
@Setter
@NoArgsConstructor
//@ToString
@Entity
@Table(name = "OnlineCourse")
public class OnlineCourse extends Course {
    @Column(name = "URL")
    private String url;

    public OnlineCourse(int credits, String title, String url) {
        super(credits, title);
        this.url = url;
    }
    @Override
    public String toString() {
        return super.toString() + "OnlineCourse{" +
                "url='" + url + '\'' +
                '}';
    }
}
