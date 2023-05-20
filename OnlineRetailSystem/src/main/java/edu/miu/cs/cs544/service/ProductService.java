package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Product;

import java.util.List;

/**
 * @author : JOHNNGUYEN
 * @since : 5/20/2023, Sat
 **/
public interface ProductService {

    Product addProduct(Product product);

    Product getProduct(Long productNumber);

    List<Product> getAllProducts();
}
