package com.otblabs.springdevtest.repository;

import java.util.Optional;

import com.otblabs.springdevtest.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	Optional<Account> findAccountByCustomerId(String customerId);

	Optional<Account> findAccountByAccountNo(String customerId);

	Optional<Account> findAccountByCustomerIdOrAccountNo(String customerId, String accountNo);
	
	Optional<Account> findAccountByCustomerIdAndAccountNo(String customerId, String accountNo);

	Account findBalanceByCustomerIdAndAccountNo(String customerId, String accountNo);

}
