package com.example.SpringBanking.service;

import com.example.SpringBanking.dto.InfoDTO;
import com.example.SpringBanking.exception.TransferParameterException;
import com.example.SpringBanking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoService {

    private final AccountRepository accountRepository;

    @Autowired
    public InfoService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public InfoDTO getAccountInfo() throws TransferParameterException {
        Long accountId = 1L;
        InfoDTO result;
        accountRepository.findById(accountId).orElseThrow(TransferParameterException::new);

        return null;
    }

}
