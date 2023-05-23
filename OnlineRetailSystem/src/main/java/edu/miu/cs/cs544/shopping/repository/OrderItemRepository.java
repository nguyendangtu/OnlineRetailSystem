package edu.miu.cs.cs544.shopping.repository;

import edu.miu.cs.cs544.shopping.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
