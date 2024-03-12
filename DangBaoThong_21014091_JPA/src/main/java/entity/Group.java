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
@Table(name = "groups")
public class Group {
    @Id
    @Column(name = "group_id")
    private int groupId;
    @Column(columnDefinition = "VARCHAR(45)")
    private String name;
}
