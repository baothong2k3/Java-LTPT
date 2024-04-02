/*
 * @ (#) Student.java    1.0    01/04/2024
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package entity;/*
 * @description:
 * @author: Bao Thong
 * @date: 01/04/2024
 * @version: 1.0
 */

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
//@ToString
@Entity
@DiscriminatorValue("Student")
public class Student extends Person{
    @Column(name = "EnrollmentDate")
    private LocalDateTime enrollmentDate;
    public Student(String firstName, String lastName, LocalDateTime enrollmentDate) {
        super(firstName, lastName);
        this.enrollmentDate = enrollmentDate;
    }

    @Override
    public String toString() {
        return super.toString() + "Student{" +
                "enrollmentDate=" + enrollmentDate +
                '}';
    }
}
