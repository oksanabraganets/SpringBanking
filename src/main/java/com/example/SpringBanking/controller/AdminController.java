package com.example.SpringBanking.controller;

import com.example.SpringBanking.dto.RequestsDTO;
import com.example.SpringBanking.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping(value = "/requests", method = RequestMethod.GET)
    public RequestsDTO getRequests(){
        System.out.println("hello in hello");
        return adminService.getCreditRequests();
    }
}
