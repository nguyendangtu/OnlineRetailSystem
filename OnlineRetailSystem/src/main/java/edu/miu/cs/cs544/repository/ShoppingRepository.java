package edu.miu.cs.cs544.repository;

import edu.miu.cs.cs544.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : JOHNNGUYEN
 * @since : 5/20/2023, Sat
 **/
public interface ShoppingRepository extends JpaRepository<ShoppingCart, Long> {
}
