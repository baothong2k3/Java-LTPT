package entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Discriminator", discriminatorType = DiscriminatorType.STRING)
@Table(name = "Person")
@NamedQueries({
        @NamedQuery(name = "Person.findAll", query = "select p from Person p"),
        @NamedQuery(name = "Person.updateBy", query = "update Person p set p.firstName = :firstName, p.lastName = :lastName where p.id = :id"),
        @NamedQuery(name = "Person.deleteBy", query = "delete from Person p where p.id = :id")
})
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PersonID", nullable = false)
    protected Integer personId;
    @Column(name = "FirstName")
    protected String firstName;
    @Column(name = "LastName")
    protected String lastName;
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}