package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Department")
public class Department {
    @Id
    @Column(name = "DepartmentID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int departmentID;

    @Column(name = "Administrator")
    private int administator;
    @Column(name = "Name")
    private String name;
    @Column(name = "Budget", columnDefinition = "float")
    private double budget;
    @Column(name = "StartDate")
    private LocalDateTime startDate;
}
