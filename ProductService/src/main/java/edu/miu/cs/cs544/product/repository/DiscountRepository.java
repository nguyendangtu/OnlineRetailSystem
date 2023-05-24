package edu.miu.cs.cs544.product.repository;

import edu.miu.cs.cs544.product.domain.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
