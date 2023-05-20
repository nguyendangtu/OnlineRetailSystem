package edu.miu.cs.cs544.repository;

import edu.miu.cs.cs544.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : JOHNNGUYEN
 * @since : 5/20/2023, Sat
 **/
public interface ProductRepository extends JpaRepository<Product, Long> {
}
