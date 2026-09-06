package com.example.demo.customer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.account.Account;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
	
	private final CustomerService cusService;
	
	@GetMapping
	public String addCustomer(Model model) {
		model.addAttribute("customer", new Customer());
		return "customer-form";
	}
	
	@PostMapping("/save")
	public String saveCustomer(Customer customer) {
		cusService.addCustomer(customer);	
		return "redirect:/customer/view";
	}
	
	@GetMapping("/view")
	public String viewCustomer(Model model) {
		model.addAttribute("customers", cusService.viewAll());
		return "customer";
	}
	
	@GetMapping("/update/{id}")
	public String updateCustomer(@PathVariable Long id, Model model) {
		Customer customer = cusService.findById(id).orElseThrow();
		model.addAttribute("customer", customer);
		return "customer-form";
	}
	
	@PostMapping("/update/{id}")
	public String updateCustomer(@PathVariable Long id, Customer customer) {
		cusService.updateCustomer(id, customer);
		return "redirect:/customer/view";
	}
	
	@PostMapping("/delete/{id}")
	public String deleteCustomer(@PathVariable Long id) {
		cusService.deleteCustomer(id);
		return "redirect:/customer/view";
	}
}





