package com.pjborowiecki.springbank.account;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findByCustomerId(Long customerId);
}
