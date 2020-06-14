package com.example.SpringBanking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PagesController {

    @RequestMapping("/api")
    public String mainPage(){
        return "index.html";
    }

    @RequestMapping("/form")
    public String regForm(){
        return "reg_form.html";
    }

    @RequestMapping("/user")
    public String userPage(){
        return "userPage.html";
    }

    @RequestMapping("/bills")
    public String billsPage(){
        return "bills.html";
    }

    @RequestMapping("/credit")
    public String creditPage(){
        return "credit.html";
    }


}