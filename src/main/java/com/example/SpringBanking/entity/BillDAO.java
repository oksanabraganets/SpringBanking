package com.example.SpringBanking.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
@Table( name="bill")
public class BillDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "user_id", nullable = false)
    private Long user;
    @Column(name = "amount", nullable = false)
    private Long amount;
    @Column(name = "recipient", nullable = false)
    private String recipient;
}
