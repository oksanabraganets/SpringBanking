package com.example.SpringBanking.controller;

import com.example.SpringBanking.dto.NameDTO;
import com.example.SpringBanking.dto.TransferDTO;
import com.example.SpringBanking.exception.TransferParameterException;
import com.example.SpringBanking.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserNameController {

    private final UserService userService;

    @Autowired
    public UserNameController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/names", method = RequestMethod.GET)
    public NameDTO getUserNames(){
        return userService.getUserBaseInfo();
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    public void executeTransfer(TransferDTO transfer){
        System.out.println(transfer);
        try {
            userService.transferMoney(transfer);
        }catch (TransferParameterException ex){
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    @ExceptionHandler({TransferParameterException.class, TransferParameterException.class})
    public ResponseEntity handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}


