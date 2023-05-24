package edu.miu.cs.cs544.product.repository;

import edu.miu.cs.cs544.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : JOHNNGUYEN
 * @since : 5/20/2023, Sat
 **/
public interface ProductRepository extends JpaRepository<Product, Long> {
}
