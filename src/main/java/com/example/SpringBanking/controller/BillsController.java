package com.example.SpringBanking.controller;

import com.example.SpringBanking.dto.BillsDTO;
import com.example.SpringBanking.dto.PaymentDTO;
import com.example.SpringBanking.exception.TransferParameterException;
import com.example.SpringBanking.service.BillsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/bills")
public class BillsController {

    private final BillsService billsService;

    @Autowired
    public BillsController(BillsService billsService){
        this.billsService = billsService;
    }

    @RequestMapping(value = "/bills", method = RequestMethod.GET)
    public BillsDTO getUserBills(){
        return billsService.getUserBills();
    }

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public void executeTransfer(PaymentDTO payment){

        System.out.println(payment);
        try {
            billsService.payTheBill(payment);
        }catch (TransferParameterException ex){
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }
}
