package com.otblabs.springdevtest.repository;

import java.util.Optional;

import com.otblabs.springdevtest.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByCustomerId(String customerId);

	// TODO : Implement the query and function below to delete a customer using Customer Id
	// @Query("?")
	// int deleteCustomerByCustomerId(String customer_id);

	// TODO : Implement the query and function below to update customer firstName using Customer Id
	// @Query("?")
	// int updateCustomerByCustomerId(String firstName, String customer_id);
	
	// TODO : Implement the query and function below and to return all customers whose Email contains  'gmail'
	// @Query("?")
	// List<Customer> findAllCustomersWhoseEmailContainsGmail();
}
