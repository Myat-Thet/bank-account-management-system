package com.example.demo.account;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.customer.Customer;
import com.example.demo.customer.CustomerDao;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

	private final AccountDao accDao;
	
	@Override
	public Account addAccount(Account account) {		
		return accDao.save(account);
	}
	
	@Override
	public List<Account> viewAll(){
		return accDao.findAll();
	}
	
	@Override
	public Optional<Account> viewById(Long id){
		return accDao.findById(id);
	}
	
	@Override
	public Account updateAccount(Long id, Account account){
		Account existing = accDao.findById(id).orElseThrow();
		existing.setAccountNumber(account.getAccountNumber());
		existing.setBalance(account.getBalance());
		return accDao.save(existing);
	}
	
	@Override
	public void deleteAccount(Long id) {
		accDao.deleteById(id);
	}
	
	@Override
	public void deposit(Long id, Account account) {
		var existingAccount= accDao.findById(id).orElseThrow(() -> new RuntimeException("Account Not Found."));
		var currentBalance = existingAccount.getBalance();
		var newBalance = currentBalance + account.getBalance();
		existingAccount.setBalance(newBalance);
		accDao.save(existingAccount);
		
	}
	@Override 
	public void withdraw(Long id, Account account) {
		var existingAccount = accDao.findById(id).orElseThrow(() -> new RuntimeException("Account not found."));
		var currentBalance = existingAccount.getBalance();
		var withdrawAmount = account.getBalance();		
		if(withdrawAmount > currentBalance) {
			throw new RuntimeException("Insufficient Amount.");
		}
		var newBalance = currentBalance - withdrawAmount;
		existingAccount.setBalance(newBalance);
		accDao.save(existingAccount);		
	}
	@Transactional
	@Override
	public void transfer(Long fromId, Long toId, double amount) {
		var sender = accDao.findById(fromId).orElseThrow();
		var receiver = accDao.findById(toId).orElseThrow();
		
		if(sender.getBalance() < amount) {
			throw new RuntimeException("Insufficient Balance.");
		}
		
		var senderBalance = sender.getBalance() - amount;
		var receiverBalance = receiver.getBalance() + amount;
		sender.setBalance(senderBalance);
		receiver.setBalance(receiverBalance);
		accDao.save(sender);
		accDao.save(receiver);
	}
	
	
}











