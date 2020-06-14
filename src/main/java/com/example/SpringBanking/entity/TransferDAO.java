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
@Table( name="transfer",
        uniqueConstraints={@UniqueConstraint(columnNames={"amount", "date"})})
public class TransferDAO {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "source_id")
    private Long sourceId;
    @Column(name = "dest_id")
    private Long destId;
    @Column(name = "amount")
    private Long amount;
    @Column(name = "date")
    private Date date;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TransferType type;

}
