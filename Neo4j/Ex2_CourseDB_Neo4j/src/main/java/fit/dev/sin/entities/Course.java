/**
 * @ (#) Course.java      3/20/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package fit.dev.sin.entities;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/20/2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @SerializedName("courseID")
    private String id;
    private String name;
    private int hours;
    private Department department;
}
