package com.example.SpringBanking.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class InfoDTO {

    List<ViewElement> viewElements;
    boolean showReplenishment;

}
