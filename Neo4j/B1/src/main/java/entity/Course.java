package entity;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Course {
    private String courseID;
    @SerializedName("name")
    private String courseName;
    private int hours;
}
