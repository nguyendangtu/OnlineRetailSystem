package edu.miu.cs.cs544.feignclient;

import edu.miu.cs.cs544.model.CreditCard;
import edu.miu.cs.cs544.model.Customer;
import edu.miu.cs.cs544.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author : JOHNNGUYEN
 * @since : 5/23/2023, Tue
 **/
@FeignClient(name = "${feign.customer.name}")
public interface CustomerFeignClient {
    @GetMapping("/customer/{customerNumber}")
    ResponseEntity<Customer> getCustomer(@PathVariable Long customerNumber);

    @GetMapping("/{creditCardNumber}")
    ResponseEntity<CreditCard> getCreditCard(@PathVariable Long customerId, @PathVariable Long creditCardNumber);
}
