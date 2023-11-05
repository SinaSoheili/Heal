package net.sinasoheili.heal.user;

import jakarta.persistence.*;
import lombok.Data;
import net.sinasoheili.heal.drug.DrugEntity;

import java.util.List;

@Entity
@Table(name = "user")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private int height;

    @OneToMany(mappedBy = DrugEntity.USER_PROP)
    private List<DrugEntity> drugs;
}
