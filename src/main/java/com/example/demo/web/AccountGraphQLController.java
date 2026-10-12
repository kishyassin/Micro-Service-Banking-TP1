package com.example.demo.web;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.demo.entities.Account;
import com.example.demo.repositories.AccountRepository;

import java.util.List;

@Controller 
public class AccountGraphQLController {

    private final AccountRepository accountRepository;

    public AccountGraphQLController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @QueryMapping
    public List<Account> accounts() {
        return accountRepository.findAll();
    }

    @QueryMapping
    public Account account(@Argument String id) {
        return accountRepository.findById(id).orElse(null);
    }
}
