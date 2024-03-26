package entity;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Department {
    @SerializedName("deptID")
    private String departmentID;
    private String name;
    private String dean;
    private String building;
    private int room;
}
