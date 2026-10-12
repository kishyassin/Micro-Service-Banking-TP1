package com.example.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AccountRequestDTO;
import com.example.demo.dto.AccountResponseDTO;
import com.example.demo.entities.Account;
import com.example.demo.enums.AccountType;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.services.AccountService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
public class AccountRestController {

    private AccountRepository accountRepository;

    public AccountRestController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @Autowired
    private AccountService accountService;
  
    @GetMapping("/accounts")
    public List<Account> accountList() {
        return accountRepository.findAll();
    }

    @GetMapping("/accounts/{id}")
    public Account getAccountById(@PathVariable String id) {
        return accountRepository.findById(id).orElse(null);
    }
    @PostMapping("/accounts")
    public AccountResponseDTO addAccount(@RequestBody AccountRequestDTO accountRequestDTO) {
        return accountService.addAccount(accountRequestDTO);
    }
    @PutMapping("accounts/{id}")
    public AccountResponseDTO updateAccount(@PathVariable String id, @RequestBody AccountRequestDTO accountRequestDTO) {
        Account accountFound = accountRepository.findById(id).get();
        if(accountFound == null) throw new RuntimeException("Account not found");
        if(accountRequestDTO.getBalance() != 0) accountFound.setBalance(accountRequestDTO.getBalance());
        if(accountRequestDTO.getType() != null) accountFound.setAccountType(AccountType.valueOf(accountRequestDTO.getType()));
        
        accountRepository.save(accountFound);
        AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
        accountResponseDTO.setId(accountFound.getId());
        accountResponseDTO.setBalance(accountFound.getBalance());
        accountResponseDTO.setType(accountFound.getAccountType().toString());
        return accountResponseDTO;
    }

    @DeleteMapping("accounts/{id}")
    public String deleteAccount(@PathVariable String id) {
        if(accountRepository.findById(id).orElse(null)== null) throw new RuntimeException("Account not found");
        accountRepository.deleteById(id);
        return "Account " + id + " deleted successfully";
    }
    
}
