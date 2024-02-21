package com.example.BankingDemoApp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double balance;

    private String description;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

}
