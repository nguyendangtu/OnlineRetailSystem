package edu.miu.cs.cs544.customer.repository;

import edu.miu.cs.cs544.customer.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : JOHNNGUYEN
 * @since : 5/21/2023, Sun
 **/
public interface AddressRepository extends JpaRepository<Address, Long> {
}
