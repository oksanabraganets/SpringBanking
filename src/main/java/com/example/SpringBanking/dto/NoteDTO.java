package com.example.SpringBanking.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class NoteDTO {

    private String firstName;
    private String firstNameUA;
    private String lastName;
    private String lastNameUA;
    private String email;
    private String password;

}
