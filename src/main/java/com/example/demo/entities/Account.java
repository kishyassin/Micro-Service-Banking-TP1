package com.example.demo.entities;

import java.sql.Date;

import com.example.demo.enums.AccountType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity 
@Data @NoArgsConstructor @AllArgsConstructor @Builder 
public class Account {
    @Id 
    private String id;
    private Date createdAt;
    private double balance;
    private String currency;
    @Enumerated (EnumType.STRING)
    private AccountType accountType;
    

}
