package edu.miu.cs.cs544.product.controller;

import edu.miu.cs.cs544.product.domain.Product;
import edu.miu.cs.cs544.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        Product result = productService.addProduct(product);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{productNumber}")
    public ResponseEntity<?> getProduct(@PathVariable Long productNumber) {
        Product product = productService.getProduct(productNumber);
        if (product == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Product with productNumber= "
                    + productNumber + " is not available"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getAllProducts() {
        List<Product> productList = productService.getAllProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

}
