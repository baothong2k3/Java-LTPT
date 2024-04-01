package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Person")
public class Person {
    @Id
    private int personId;
    private String firstName;
    private String lastName;
}
@Entity
@Table(name = "Instruction")
public class Instruction extends Person{
    @Id
    private int instructionId;
    private String instruction;
}
