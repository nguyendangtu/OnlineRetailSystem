package edu.miu.cs.cs544.shopping.repository;

import edu.miu.cs.cs544.shopping.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findById(Long id);

    @Query("select o from Order o where o.customerId = :customerId")
    List<Order> findByCustomerId(Long customerId);
}
