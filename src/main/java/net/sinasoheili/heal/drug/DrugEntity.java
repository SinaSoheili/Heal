package net.sinasoheili.heal.drug;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sinasoheili.heal.user.UserEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "drug")
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
