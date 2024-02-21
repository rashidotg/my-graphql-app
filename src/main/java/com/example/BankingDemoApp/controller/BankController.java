package com.example.BankingDemoApp.controller;

import com.example.BankingDemoApp.model.Bank;
import com.example.BankingDemoApp.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BankController {

    @Autowired
    private BankRepository bankRepository;

    @QueryMapping
    public List<Bank> findAllBanks() {
        return bankRepository.findAll();
    }

    @QueryMapping
    public Integer countBanks() {
        return Math.toIntExact(bankRepository.count());
    }

    @MutationMapping
    public Bank createBank(@Argument String name, @Argument Integer routing) {
        return bankRepository.save(new Bank(name, routing));
    }
}
