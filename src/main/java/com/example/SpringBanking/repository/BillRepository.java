package com.example.SpringBanking.repository;

import com.example.SpringBanking.entity.BillDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<BillDAO, Long> {

    List<BillDAO> findAllByUser(Long id);

}
