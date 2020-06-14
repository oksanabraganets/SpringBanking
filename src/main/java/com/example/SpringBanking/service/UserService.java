package com.example.SpringBanking.service;

import com.example.SpringBanking.dto.NameDTO;
import com.example.SpringBanking.dto.TransferDTO;
import com.example.SpringBanking.entity.*;
import com.example.SpringBanking.repository.AccountRepository;
import com.example.SpringBanking.repository.TransferRepository;
import com.example.SpringBanking.repository.UserRepository;
import com.example.SpringBanking.exception.TransferParameterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;
    private static UserDAO currentUser;

    public static UserDAO getCurrentUser(){ return currentUser; };

    @Autowired
    public UserService(UserRepository userRepository,
                       AccountRepository accountRepository,
                       TransferRepository transferRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
    }

    public NameDTO getUserBaseInfo(){
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, LocaleService.getLocale());
        String date = df.format(new java.sql.Date(System.currentTimeMillis()));
        currentUser = userRepository.findByEmail(currentUser.getEmail()).orElseThrow(
                () -> new UsernameNotFoundException("Email " + currentUser.getEmail() +" was not found."));
        NameDTO nameDTO = NameDTO.builder()
                .firstName(currentUser.getFirstName())
                .lastName(currentUser.getLastName())
                .accounts(currentUser.getAccounts())
                .allAccounts(accountRepository.findAll())
                .date(date)
                .build();

        return nameDTO;
    }

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        currentUser = userRepository.findByEmail(s).orElseThrow(
                () -> new UsernameNotFoundException("Email " + s +" was not found."));

        List<Role> roles = new ArrayList<>();
        roles.add(currentUser.getRole());
        String pass = currentUser.getPassword();

        User userDetails = (User) User.builder()
                .username(s)
                .password(pass)
                .authorities(roles)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
        userDetails.setAccountNonExpired(true);
        System.out.println(userDetails);
        return userDetails;
    }
    public void transferMoney(TransferDTO transfer) throws TransferParameterException{
        transferMoneyTransactional(TransferDAO.builder()
                .sourceId(transfer.getFromId())
                .destId(transfer.getToId())
                .amount(transfer.getAmount())
                .date(new java.sql.Date(System.currentTimeMillis()))
                .type(TransferType.TRANSFER)
                .build());
    }

    @Transactional
    private void transferMoneyTransactional(TransferDAO transfer) throws TransferParameterException {
        AccountDAO fromAccount = accountRepository.findById(transfer.getSourceId()).orElseThrow(
                () -> new TransferParameterException("From account doesn't exist") );
        AccountDAO toAccount = accountRepository.findById(transfer.getDestId()).orElseThrow(
                () -> new TransferParameterException("To account doesn't exist") );
        if (fromAccount.getType().name().equals("DEPOSIT") &&
                fromAccount.getBalance() - transfer.getAmount() >= 0) {
            fromAccount.setBalance(fromAccount.getBalance() - transfer.getAmount());
        }else
            throw  new TransferParameterException("Not enough money on account");
        toAccount.setBalance(toAccount.getBalance() + transfer.getAmount());
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        transferRepository.save(transfer);
    }
}
