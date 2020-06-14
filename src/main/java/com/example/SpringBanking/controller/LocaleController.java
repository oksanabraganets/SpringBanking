package com.example.SpringBanking.controller;

import com.example.SpringBanking.service.LocaleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RestController
@RequestMapping(value = "/locale")
public class LocaleController {

    @RequestMapping(value = "/ua", method = RequestMethod.GET)
    public void setLocaleUA(){
        System.out.println("Setting ua locale");
        LocaleService.setLocale(new Locale("ua"));
    }

    @RequestMapping(value = "/us", method = RequestMethod.GET)
    public void setLocaleEN(){
        System.out.println("Setting ua locale");
        LocaleService.setLocale(Locale.US);
    }

}
