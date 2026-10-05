package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Account;


public interface AccountRepository extends JpaRepository<Account, String> {
    public Optional<Account> findById(String id);
    public List<Account> findAll();
}
