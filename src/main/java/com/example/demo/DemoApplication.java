package com.example.demo;
import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.entities.Account;
import com.example.demo.enums.AccountType;
import com.example.demo.repositories.AccountRepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner start(AccountRepository bankAccountRepository){
		return args -> {
			for (int i =0; i<10 ; i++){
				Account account = Account.builder()
					.id(UUID.randomUUID().toString())
					.createdAt(Date.valueOf(LocalDate.now()))
					.balance(Math.random() * 10000)
					.currency("MAD")
					.accountType(AccountType.CURRENT_ACCOUNT)
					.build();
				bankAccountRepository.save(account);
			}
		};
	}

}
