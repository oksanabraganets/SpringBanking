package com.example.SpringBanking.dto;

import com.example.SpringBanking.entity.AccountDAO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreditDTO {
    private String firstName;
    private String lastName;
    private boolean creditExists;
    private AccountDAO credit;
}
