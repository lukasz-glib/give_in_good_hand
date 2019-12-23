package pl.lg.charity.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = "password")
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Boolean active = Boolean.FALSE;
    @ManyToMany
    private Set<Role> roles = new HashSet<>();
    @OneToMany (mappedBy = "user")
    private List<Donation> donations = new ArrayList<>();
}
