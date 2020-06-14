package com.example.SpringBanking.service;

import com.example.SpringBanking.dto.NoteDTO;
import com.example.SpringBanking.entity.Role;
import com.example.SpringBanking.entity.UserDAO;
import com.example.SpringBanking.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RegFormService {
    private final UserRepository userRepository;

    @Autowired
    public RegFormService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveNewUser (NoteDTO user){
        String password = new BCryptPasswordEncoder().encode(user.getPassword());
        System.out.println(password);
        UserDAO userDAO = UserDAO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(Role.ROLE_USER)
                .email(user.getEmail())
                .password(password)
                .firstNameUA(user.getFirstNameUA())
                .lastNameUA(user.getLastNameUA())
                .build();
        log.info("{}", userDAO);
        //TODO inform the user about the replay email
        // TODO exception to endpoint
        try {
            userRepository.save(userDAO);
        } catch (Exception ex){
            log.info("{Почтовый адрес уже существует}");
        }

    }

}
