package com.example.SpringBanking.service;

import com.example.SpringBanking.dto.BillsDTO;
import com.example.SpringBanking.dto.PaymentDTO;
import com.example.SpringBanking.entity.*;
import com.example.SpringBanking.exception.TransferParameterException;
import com.example.SpringBanking.repository.AccountRepository;
import com.example.SpringBanking.repository.BillRepository;
import com.example.SpringBanking.repository.TransferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
public class BillsService {

    private final BillRepository billRepository;
    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;

    @Autowired
    public BillsService(BillRepository billRepository,
                        AccountRepository accountRepository, TransferRepository transferRepository){
        this.billRepository = billRepository;
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
    }

    public BillsDTO getUserBills(){
        return BillsDTO.builder()
                .firstName(UserService.getCurrentUser().getFirstName())
                .lastName(UserService.getCurrentUser().getLastName())
                .bills(billRepository.findAllByUser(UserService.getCurrentUser().getId()))
                .accounts(UserService.getCurrentUser().getAccounts())
                .build();
    }

    public void payTheBill(PaymentDTO payment) throws TransferParameterException {
        AccountDAO account = accountRepository.findById(payment.getAccountId()).orElseThrow(
                TransferParameterException::new);
        BillDAO bill = billRepository.findById(payment.getBillId()).orElseThrow(TransferParameterException::new);
        if ( ! isEnoughMoneyOnAccount(account, bill.getAmount()))
            throw  new TransferParameterException();
        TransferDAO transfer = TransferDAO.builder()
                .sourceId(payment.getAccountId())
                .destId(null)
                .amount(bill.getAmount())
                .date(new java.sql.Date(System.currentTimeMillis()))
                .type(TransferType.BILL)
                .build();
        account.setBalance(account.getBalance() - bill.getAmount());
        payBillAndWriteHistory(account, transfer, bill);
    }

    @Transactional
    private void payBillAndWriteHistory(AccountDAO account, TransferDAO transfer, BillDAO bill) {
        accountRepository.save(account);
        transferRepository.save(transfer);
        billRepository.delete(bill);
    }

    private boolean isEnoughMoneyOnAccount(AccountDAO account, Long billAmount){
        if ( account.getType() == AccountType.DEPOSIT ){
            return account.getBalance() >= billAmount;
        }else{
            return (account.getCredit_limit() + account.getBalance()) >= billAmount;
        }
    }
}
