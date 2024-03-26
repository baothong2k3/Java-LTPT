/**
 * @ (#) Department.java      3/20/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package fit.dev.sin.entities;

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
public class Department {

    private String deptID;
    private String name;
    private String dean;
    private String building;
    private int room;

}
