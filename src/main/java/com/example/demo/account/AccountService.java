package com.example.demo.account;

import java.util.List;
import java.util.Optional;

import com.example.demo.customer.Customer;

public interface AccountService {
	
	Account addAccount(Account account);
	List<Account> viewAll();
	Optional<Account> viewById(Long id);
	Account updateAccount(Long id, Account account);
	void deleteAccount(Long id);	
	void deposit(Long id, Account account);
	void withdraw(Long id, Account account);
	void transfer(Long fromId, Long toId, double amount);
}
