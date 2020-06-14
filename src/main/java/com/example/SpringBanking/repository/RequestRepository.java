package com.example.SpringBanking.repository;

import com.example.SpringBanking.entity.RequestDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<RequestDAO, Long> {
}
