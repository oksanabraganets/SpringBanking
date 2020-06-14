package com.example.SpringBanking.dto;

import com.example.SpringBanking.entity.AccountDAO;
import com.example.SpringBanking.entity.BillDAO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BillsDTO {
    private String firstName;
    private String lastName;
    List<BillDAO> bills;
    List<AccountDAO> accounts;
}
