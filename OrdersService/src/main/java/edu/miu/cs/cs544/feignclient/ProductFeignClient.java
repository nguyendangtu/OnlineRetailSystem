package edu.miu.cs.cs544.feignclient;

import edu.miu.cs.cs544.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author : JOHNNGUYEN
 * @since : 5/23/2023, Tue
 **/
@FeignClient(name = "${feign.product.name}")
public interface ProductFeignClient {
    @GetMapping("/product/{productNumber}")
    ResponseEntity<Product> getProduct(@PathVariable Long productNumber);
}
