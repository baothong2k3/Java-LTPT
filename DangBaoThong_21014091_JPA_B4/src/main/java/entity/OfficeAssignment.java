/*
 * @ (#) OfficeAssignment.java    1.0    01/04/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package entity;/*
 * @description:
 * @author: Bao Thong
 * @date: 01/04/2024
 * @version: 1.0
 */

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "OfficeAssignment")
public class OfficeAssignment {
    @Column(name = "Location")
    private String location;
    @Column(name = "Timestamp")
    private Timestamp timestamp;

    @OneToOne
    @Id
    @JoinColumn(name = "InstructorID")
    private Instructor instructor;
}
