package com.otblabs.springdevtest.repository;

import java.util.List;
import java.util.Optional;

import com.otblabs.springdevtest.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Service
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByCustomerId(String customerId);

//	// TODO : Implement the query and function below to delete a customer using Customer Id
//	@Query("DELETE from customers WHERE customerId = :customerId")
//	int deleteCustomerByCustomerId(@Param("customerId")String customer_id);


	// TODO : Implement the query and function below to update customer firstName using Customer Id
	// @Query("?")
	// int updateCustomerByCustomerId(String firstName, String customer_id);
	//@Modifying
	// @Query("UPDATE customers u SET u.firstName = :firstName WHERE u.customerId = :customerId")
	// int updateCustomerByCustomerId(@Param("firstName") String firstName, @Param("customerId")String customer_id);
	
	// TODO : Implement the query and function below and to return all customers whose Email contains  'gmail'
	// @Query("?")
//	@Query("SELECT m FROM customers m WHERE m.email LIKE ?1%")
//	List<Customer> findAllCustomersWhoseEmailContainsGmail(String gmail);
}
