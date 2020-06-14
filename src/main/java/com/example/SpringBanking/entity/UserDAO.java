package com.example.SpringBanking.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table( name="user",
        uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
public class UserDAO {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column(name = "iduser", nullable = false)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String email;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(nullable = false)
    private String password;
    @Column(name = "first_name_ua", nullable = false)
    private String firstNameUA;
    @Column(name = "last_name_ua", nullable = false)
    private String lastNameUA;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "user_account_link",
            joinColumns = @JoinColumn(name = "iduser", referencedColumnName = "iduser"),
            inverseJoinColumns = @JoinColumn(name = "idaccount", referencedColumnName = "idaccount")
    )
    private List<AccountDAO> accounts;

    @Override
    public String toString() {
        return "UserDAO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", password='" + password + '\'' +
                ", firstNameUA='" + firstNameUA + '\'' +
                ", lastNameUA='" + lastNameUA + '\'' +
                '}';
    }
}
