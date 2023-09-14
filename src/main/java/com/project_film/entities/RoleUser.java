package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import models.ERoleUser;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class RoleUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERoleUser name;
}
