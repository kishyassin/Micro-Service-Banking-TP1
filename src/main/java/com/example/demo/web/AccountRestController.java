package com.example.demo.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Account;
import com.example.demo.repositories.AccountRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
public class AccountRestController {

    private AccountRepository accountRepository;

    public AccountRestController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
  
    @GetMapping("/accounts")
    public List<Account> accountList() {
        return accountRepository.findAll();
    }

    @GetMapping("/accounts/{id}")
    public Account getAccountById(@PathVariable String id) {
        return accountRepository.findById(id).orElse(null);
    }
    @PostMapping("/accounts")
    public String addAccount(@RequestBody Account account) {
        accountRepository.save(account);
        return account.toString();
    }
    @PutMapping("accounts/{id}")
    public String updateAccount(@PathVariable String id, @RequestBody Account account) {
        Account accountFound = accountRepository.findById(id).get();
        if(accountFound == null) throw new RuntimeException("Account not found");
        if(account.getBalance() != 0) accountFound.setBalance(account.getBalance());
        if(account.getCreatedAt() != null) accountFound.setCreatedAt(account.getCreatedAt());
        if(account.getAccountType() != null) accountFound.setAccountType(account.getAccountType());
        if(account.getCurrency() != null) accountFound.setCurrency(account.getCurrency());
        
        accountRepository.save(accountFound);
        return accountFound.toString();
    }

    @DeleteMapping("accounts/{id}")
    public String deleteAccount(@PathVariable String id) {
        Account accountFound = accountRepository.findById(id).get();
        if(accountFound == null) throw new RuntimeException("Account not found");
        accountRepository.delete(accountFound);
        return accountFound.toString();
    }
    
}
