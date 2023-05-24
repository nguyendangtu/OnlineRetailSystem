package edu.miu.cs.cs544.product.controller;

import edu.miu.cs.cs544.product.domain.Product;
import edu.miu.cs.cs544.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : JOHNNGUYEN
 * @since : 5/20/2023, Sat
 **/
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product result = productService.addProduct(product);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{productNumber}")
    public ResponseEntity<Product> getProduct(@PathVariable Long productNumber) {
        Product product = productService.getProduct(productNumber);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/{productNumber}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productNumber, @RequestBody Product product) {
        product = productService.updateProduct(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{productNumber}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long productNumber) {
        productService.deleteProduct(productNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Page<Product>> getAllProducts(Pageable pageable) {
        Page<Product> productList = productService.getAllProducts(pageable);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

}
