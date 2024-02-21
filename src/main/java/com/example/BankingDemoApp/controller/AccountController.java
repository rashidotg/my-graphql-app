package com.example.BankingDemoApp.controller;

import com.example.BankingDemoApp.model.Account;
import com.example.BankingDemoApp.model.Bank;
import com.example.BankingDemoApp.repository.AccountRepository;
import com.example.BankingDemoApp.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BankRepository bankRepository;

    @QueryMapping
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    @QueryMapping
    public Account findAccountById(@Argument Integer id) {
        return accountRepository.findById(id.longValue()).orElse(null);
    }

    @QueryMapping
    public Integer countAccounts() {
        return Math.toIntExact(accountRepository.count());
    }

    @MutationMapping
    public Account createAccount(@Argument Double balance, @Argument String description, @Argument Long bankId) {
        Bank bank = bankRepository.findById(bankId).orElse(null);

        Account newAccount = new Account();
        newAccount.setBalance(balance);
        newAccount.setDescription(description);
        newAccount.setBank(bank);
        return accountRepository.save(newAccount);
    }

    @MutationMapping
    private Account updateAccount(@Argument Integer id, @Argument Double balance, @Argument String description){
        Account account = accountRepository.findById(id.longValue()).orElse(null);
        if(account != null){
            account.setBalance(balance);
            account.setDescription(description);
            accountRepository.save(account);
        }
        return account;
    }

    @MutationMapping
    public Boolean deleteAccount(Integer id){
        accountRepository.delete(accountRepository.findById(id.longValue()).orElse(null));
        return true;
    }
}
