package com.example.SpringBanking.service;

import com.example.SpringBanking.dto.RequestsDTO;
import com.example.SpringBanking.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final RequestRepository requestRepository;

    @Autowired
    public AdminService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public RequestsDTO getCreditRequests(){
        RequestsDTO requestsDTO = RequestsDTO.builder()
                .firstName(UserService.getCurrentUser().getFirstName())
                .lastName(UserService.getCurrentUser().getLastName())
                .requests(requestRepository.findAll())
                .build();
        return requestsDTO;
    }
}
