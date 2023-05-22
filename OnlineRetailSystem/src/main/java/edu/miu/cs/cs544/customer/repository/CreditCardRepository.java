package edu.miu.cs.cs544.customer.repository;

import edu.miu.cs.cs544.customer.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
