package com.example.SpringBanking.entity;

import lombok.*;
import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
@Table( name="account")
public class AccountDAO {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column(name = "idaccount", nullable = false)
    private Long id;
    @Column(name = "balance", nullable = false)
    private Long balance;
    @Column(name = "validity")
    private Date validity;
    @Column(name = "rate")
    private Long rate;
    @Column(name = "accrued")
    private Long accrued;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AccountType type;
    @Column(name = "credit_limit")
    private Long credit_limit;
    @Column(name = "debt")
    private Long debt;
}
