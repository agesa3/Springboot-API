package com.otblabs.springdevtest.controller;

import java.util.List;

import com.otblabs.springdevtest.exception.ResourceNotFoundException;
import com.otblabs.springdevtest.model.Account;
import com.otblabs.springdevtest.repository.AccountRepository;
import com.otblabs.springdevtest.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(AppUtils.BASE_URL + "/accounts")
public class AccountController {
	private Gson gson = new Gson();

	@Autowired
	private AccountRepository accountRepository;

	@GetMapping("/")
	public List<Account> getAllAccount() {
		return accountRepository.findAll();
	}

	@GetMapping("/{searchId}")
	public ResponseEntity<?> getAccountByCustomerIdOrAccountNo(
			@PathVariable(value = "searchId") String customerIdOrAccountNo) throws ResourceNotFoundException {
		Account account = accountRepository
				.findAccountByCustomerIdOrAccountNo(customerIdOrAccountNo, customerIdOrAccountNo)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Account not found for this searchId :: " + customerIdOrAccountNo));

		return ResponseEntity.ok().body(account);
	}

	@GetMapping("/balance")
	public ResponseEntity<?> getAccountBalanceByCustomerIdAndAccountNo(@RequestBody String request)
			throws ResourceNotFoundException {
		try {
			JsonObject response = new JsonObject();

			final JsonObject balanceRequest = gson.fromJson(request, JsonObject.class);
			String customerId = balanceRequest.get("customerId").getAsString();
			String accountNo = balanceRequest.get("accountNo").getAsString();

			// TODO : Add logic to find account balance by CustomerId And
			// AccountNo
			Account account = null;
			if (account !=null) {
				account = accountRepository.findBalanceByCustomerIdAndAccountNo(customerId, accountNo);
			}
			response.addProperty("balance", account.getBalance());
			return ResponseEntity.ok().body(gson.toJson(response));
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@PostMapping("/")
	public Account createAccount(@RequestBody Account account) {
		return accountRepository.save(account);
	}

}
