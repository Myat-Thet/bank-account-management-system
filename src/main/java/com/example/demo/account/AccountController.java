package com.example.demo.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

	private final AccountService accService;
	
	@GetMapping
	public String addAccount(Model model) {
		model.addAttribute("account", new Account());
		return "account-form";
	}
	
	@PostMapping("/save")
	public String saveAccount(Account account) {
		accService.addAccount(account);
		return "redirect:/account/view";
	}
	
	@GetMapping("/view")
	public String viewAccount(Model model) {
		model.addAttribute("accounts", accService.viewAll());
		return "account";
	}
	
	@GetMapping("/view/{id}")
	public String viewById(@PathVariable Long id, Model model) {
		Account account = accService.viewById(id).orElseThrow();
		model.addAttribute("account", account);
		return "account-detail";
	}
	
	@GetMapping("/update/{id}")
	public String updateById(@PathVariable Long id, Model model) {
		Account acc = accService.viewById(id).orElseThrow();
		model.addAttribute("account", acc);
		return "account-form";
	}
	
	@PostMapping("/update/{id}")
	public String updateAccount(@PathVariable Long id, Account account) {
		accService.updateAccount(id, account);
		return "redirect:/account/view";
	}
	
	@PostMapping("/{id}/deposit")
	public String deposit(@PathVariable Long id, Account account) {
		accService.deposit(id, account);
		return "redirect:/account/view";
	}
	
	@PostMapping("/{id}/withdraw")
	public String withdraw(@PathVariable Long id, Account account) {
		accService.withdraw(id, account);
		return "redirect:/account/view";
	}
	
	@PostMapping("/transfer")
	public String transfer(
							@RequestParam(value = "fromId") Long fromId,
							@RequestParam(value = "toId")Long toId, 
							Account account) {
		accService.transfer(fromId, toId, account.getBalance());
		return "redirect:/account/view";
	}
	
}





