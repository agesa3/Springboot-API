package com.otblabs.springdevtest.repository;

import java.util.List;
import java.util.Optional;

import com.otblabs.springdevtest.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	Optional<List<Transaction>> findTransactionsByCustomerId(String customerId);

	Optional<List<Transaction>> findTransactionsByTransactionId(String transactionId);

	Optional<List<Transaction>> findTransactionsByCustomerIdOrTransactionId(String transactionId, String customerId);

	// TODO : Change below Query to return the last 5 transactions
	// TODO : Change below Query to use Named Parameters instead of indexed
	// parameters
	// TODO : Change below function to return Optional<List<Transaction>>
	@Query("SELECT t FROM Transaction t WHERE t.customerId =?1 AND  t.accountNo =?2")
	List<Transaction> getMiniStatementUsingCustomerIdAndAccountNo(String customer_id, String account_no);
//correct
//	@Query("SELECT t FROM transactions t WHERE t.customerId =:customerid AND  t.accountNo = :accountno LIMIT 5")
//	Optional<List<Transaction>> getMiniStatementUsingCustomerIdAndAccountNo(@Param("customerid")String customer_id, @Param("accountno") String account_no);
}
