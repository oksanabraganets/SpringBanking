package com.example.SpringBanking.repository;

import com.example.SpringBanking.entity.AccountDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountDAO, Long> {

}
