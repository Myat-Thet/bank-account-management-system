package com.example.demo.customer;

import java.util.List;
import java.util.Optional;

import com.example.demo.account.Account;

public interface CustomerService {
	
	Customer addCustomer(Customer customer);
	List<Customer> viewAll();
	Customer updateCustomer(Long id, Customer customer);
	void deleteCustomer(Long id);
	Optional<Customer> findById(Long id);
}
