package com.pjborowiecki.springbank.loan;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {

    List<Loan> findByCustomerIdOrderByStartDateDesc(Long customerId);

}
