package com.example.SpringBanking.repository;

import com.example.SpringBanking.entity.TransferDAO;
import com.example.SpringBanking.entity.TransferType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<TransferDAO, Long> {

    TransferDAO save(TransferDAO account);

    List<TransferDAO> findAllByType(TransferType type);
}

