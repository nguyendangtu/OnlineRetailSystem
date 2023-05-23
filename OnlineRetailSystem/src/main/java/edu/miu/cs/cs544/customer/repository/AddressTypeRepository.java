package edu.miu.cs.cs544.customer.repository;

import edu.miu.cs.cs544.customer.domain.AddressType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressTypeRepository extends JpaRepository<AddressType, Long> {
}
