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
@Table( name="request")
public class RequestDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    @Column(name = "user_id", nullable = false)
    Long user;
    @Column(name = "credit_limit", nullable = false)
    Long limit;
}
