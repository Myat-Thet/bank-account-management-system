package com.example.demo.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.account.Account;
import com.example.demo.account.AccountDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
	
	private final CustomerDao cusDao;

	@Override
	public Customer addCustomer(Customer customer) {
		return cusDao.save(customer);
	}
	
	@Override
	public List<Customer> viewAll(){
		return cusDao.findAll();
	}
	
	@Override
	public Customer updateCustomer(Long id, Customer customer){
		Customer existing = cusDao.findById(id).orElseThrow();
		existing.setName(customer.getName());
		return cusDao.save(existing);
	}
	
	@Override
	public void deleteCustomer(Long id) {
		cusDao.deleteById(id);
	}

	@Override
	public Optional<Customer> findById(Long id) {
		return cusDao.findById(id);
	}

}
