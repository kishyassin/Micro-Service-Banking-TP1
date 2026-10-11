package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.example.demo.dto.AccountRequestDTO;
import com.example.demo.dto.AccountResponseDTO;
import com.example.demo.entities.Account;
import com.example.demo.enums.AccountType;
import com.example.demo.repositories.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Override
    public AccountResponseDTO addAccount(AccountRequestDTO accountRequestDTO) {
        Account account=Account.builder()
        .balance(accountRequestDTO.getBalance())
        .accountType(AccountType.valueOf(accountRequestDTO.getType()))
        .build();
        Account savedAccount = accountRepository.save(account);
        AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
        accountResponseDTO.setId(savedAccount.getId());
        accountResponseDTO.setBalance(savedAccount.getBalance());
        accountResponseDTO.setType(savedAccount.getAccountType().toString());
        return accountResponseDTO;
    }

}
