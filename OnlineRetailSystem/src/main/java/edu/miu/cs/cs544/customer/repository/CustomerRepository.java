package edu.miu.cs.cs544.customer.repository;

import edu.miu.cs.cs544.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : JOHNNGUYEN
 * @since : 5/20/2023, Sat
 **/
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
