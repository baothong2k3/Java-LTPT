/*
 * @ (#) Instructor.java    1.0    01/04/2024
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
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Instructor extends Person{
    @Column(name = "HireDate")
    private LocalDateTime hireDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "CourseInstructor",
            joinColumns = @JoinColumn(name = "PersonID"),
            inverseJoinColumns = @JoinColumn(name = "CourseID")
    )
    protected Set<Course> courses;
    public Instructor(String firstName, String lastName, LocalDateTime hireDate) {
        super(firstName, lastName);
        this.hireDate = hireDate;
    }
    @Override
    public String toString() {
        return super.toString() + "Instructor{" +
                "hireDate=" + hireDate +
                '}';
    }
}
