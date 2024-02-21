package com.example.BankingDemoApp.repository;

import com.example.BankingDemoApp.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {
}

