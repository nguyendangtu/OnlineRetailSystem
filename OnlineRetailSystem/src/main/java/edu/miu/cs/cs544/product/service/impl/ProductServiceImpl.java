package edu.miu.cs.cs544.product.service.impl;

import edu.miu.cs.cs544.product.domain.Product;
import edu.miu.cs.cs544.product.repository.ProductRepository;
import edu.miu.cs.cs544.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * @author : JOHNNGUYEN
 * @since : 5/20/2023, Sat
 **/
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Product getProduct(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);

    }
}
