package net.sinasoheili.heal.drug;

import jakarta.persistence.*;
import lombok.Data;
import net.sinasoheili.heal.user.UserEntity;

@Entity
@Table(name = "drug")
@Data
public class DrugEntity {

    public static final String USER_PROP = "user";

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private UserEntity user;

}
