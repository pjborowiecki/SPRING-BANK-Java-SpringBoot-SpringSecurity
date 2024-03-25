package com.pjborowiecki.springbank.transaction;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, String> {

    List<Transaction> findByCustomerIdOrderByTransactionDataDesc(Long customerId);
}
