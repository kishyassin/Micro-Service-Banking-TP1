package com.example.demo.services;

import com.example.demo.dto.AccountRequestDTO;
import com.example.demo.dto.AccountResponseDTO;

public interface AccountService {
    public AccountResponseDTO addAccount(AccountRequestDTO accountRequestDTO);
}
