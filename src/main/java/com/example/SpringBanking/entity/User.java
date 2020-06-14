package com.example.SpringBanking.entity;

import lombok.*;

import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
public class User implements UserDetails {
    List<Role> authorities;
    String password;
    String username;
    boolean accountNonExpired;
    boolean accountNonLocked;
    boolean credentialsNonExpired;
    boolean enabled;

}
