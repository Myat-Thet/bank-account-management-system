package com.example.demo.account;

import com.example.demo.customer.Customer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String accountNumber;
	private double balance;
	
	@ManyToOne
	private Customer customer;
	
	public void addCustomer(Customer customer) {
		this.customer = customer;
	}

	public Account(String accountNumber, double balance, Customer customer) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.customer = customer;
	}
}
