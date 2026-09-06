package com.example.demo.customer;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.account.Account;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@OneToMany(mappedBy = "customer")
	private List<Account> accounts = new ArrayList<>();
	
	public void addAccount(Account account) {
		this.accounts.add(account);
	}
	
	public Customer(String name, List<Account> account) {
		super();
		this.name = name;
		this.accounts = account;
	}		
}
