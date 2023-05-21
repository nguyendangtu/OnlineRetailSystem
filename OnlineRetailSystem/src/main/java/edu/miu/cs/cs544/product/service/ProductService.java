package edu.miu.cs.cs544.product.service;

import edu.miu.cs.cs544.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * @author : JOHNNGUYEN
 * @since : 5/20/2023, Sat
 **/
public interface ProductService {

    Product addProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Long productId);

    Product getProduct(Long productId);

    Page<Product> getAllProducts(Pageable pageable);
}
