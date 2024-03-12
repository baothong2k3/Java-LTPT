package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "users")
public class user {
    @Id
    @Column(name = "user_id")
    private int userId;
    @Column(columnDefinition = "VARCHAR(45)")
    private String name;
    @Column(columnDefinition = "VARCHAR(45)")
    private String password;
    @Column(columnDefinition = "VARCHAR(45)")
    private String email;
}
