package com.pjborowiecki.springbank.card;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {

    List<Card> findByCustomerId(Long customerId);

}
