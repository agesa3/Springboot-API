package com.otblabs.springdevtest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.otblabs.springdevtest.exception.ResourceNotFoundException;
import com.otblabs.springdevtest.model.Account;
import com.otblabs.springdevtest.model.Customer;
import com.otblabs.springdevtest.repository.AccountRepository;
import com.otblabs.springdevtest.repository.CustomerRepository;
import com.otblabs.springdevtest.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(AppUtils.BASE_URL + "/customers")

public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;

    /**
     * Login
     *
     * @param request
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<?> customerLogin(@RequestBody String request) {
        try {

            return ResponseEntity.status(200).body(HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerByCustomerId(@PathVariable(value = "customerId") String customerId)
            throws ResourceNotFoundException {
        Customer customer = customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
        return ResponseEntity.ok().body(customer);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        try {
            // TODO : Add logic to Hash Customer PIN here
            String pin = customer.getPin();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPin = passwordEncoder.encode(pin);
            customer.setPin(encodedPin);
//            // TODO : Add logic to check if Customer with provided username, or
            // customerId exists. If exists, throw a Customer with [?] exists
            // Exception.
            String customerId = customer.getCustomerId();
            Optional<Customer> existingCustomer = customerRepository.findByCustomerId(customerId);
            if (existingCustomer.isPresent()) {
                throw new Exception("Customer with " + customerId + " exits");
            }

            String accountNo = generateAccountNo(customer.getCustomerId());
            Account account = new Account();
            account.setCustomerId(customer.getCustomerId());
            account.setAccountNo(accountNo);
            account.setBalance(0.0);
            accountRepository.save(account);

            return ResponseEntity.ok().body(customerRepository.save(customer));
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "customerId") String customerId,
                                                   @RequestBody Customer customerDetails) throws ResourceNotFoundException {
        Customer customer = customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));

        customer.setEmail(customerDetails.getEmail());
        customer.setLastName(customerDetails.getLastName());
        customer.setFirstName(customerDetails.getFirstName());
        final Customer updatedCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{customerId}")
    public Map<String, Boolean> deleteCustomer(@PathVariable(value = "customerId") String customerId)
            throws ResourceNotFoundException {
        Customer customer = customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));

        customerRepository.delete(customer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    /**
     * generate a random but unique Account No (NB: Account No should be unique in
     * your accounts table)
     */
    private String generateAccountNo(String customerId) {
        // TODO : Add logic here - generate a random but unique Account No (NB:
        // Account No should be unique in the accounts table)
        String accountNo = randomNumber(10);

        // Check if accountNo exists
        Optional<Account> existingAccountNo = accountRepository.findAccountByCustomerIdOrAccountNo(customerId, accountNo);
        // If exists generate another random Account number
        if (existingAccountNo.isPresent()) {
            accountNo = randomNumber(10);
        }
        return accountNo;

    }

    private static final String NUMBERS = "0123456789";

    private static String randomNumber(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * NUMBERS.length());
            builder.append(NUMBERS.charAt(character));

        }
        return builder.toString();
    }
}


