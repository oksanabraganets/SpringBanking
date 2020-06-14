package com.example.SpringBanking.service;

import com.example.SpringBanking.dto.BillsDTO;
import com.example.SpringBanking.dto.PaymentDTO;
import com.example.SpringBanking.entity.AccountDAO;
import com.example.SpringBanking.entity.BillDAO;
import com.example.SpringBanking.entity.TransferDAO;
import com.example.SpringBanking.entity.TransferType;
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
                .accounts(null/*accountRepository.findAllByUser(UserService.getCurrentUser().getId())*/)
                .build();
    }

    @Transactional
    public void payTheBill(PaymentDTO payment) throws TransferParameterException {
        AccountDAO account = accountRepository.findById(payment.getAccountId()).orElseThrow(
                TransferParameterException::new);
        BillDAO bill = billRepository.findById(payment.getBillId()).orElseThrow(TransferParameterException::new);
        if (account.getType().name().equals("DEPOSIT") &&
                account.getBalance() - bill.getAmount() >= 0) {
            account.setBalance(account.getBalance() - bill.getAmount());
        }else
            throw  new TransferParameterException();
        accountRepository.save(account);
        transferRepository.save(TransferDAO.builder()
                .sourceId(payment.getAccountId())
                .destId(null)
                .amount(bill.getAmount())
                .date(new java.sql.Date(System.currentTimeMillis()))
                .type(TransferType.BILL)
                .build()
        );
        billRepository.delete(bill);
    }
}
