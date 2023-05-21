package edu.miu.cs.cs544.customer.controller;


import edu.miu.cs.cs544.customer.domain.Customer;
import edu.miu.cs.cs544.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : JOHNNGUYEN
 * @since : 5/20/2023, Sat
 **/
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        Customer result = customerService.addCustomer(customer);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{customerNumber}")
    public ResponseEntity<?> getCustomer(@PathVariable Long customerNumber) {
        Customer customer = customerService.getCustomer(customerNumber);
        if (customer == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Customer with CustomerNumber= " + customerNumber + " is not available"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PutMapping("/{customerNumber}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long customerNumber, @RequestBody Customer customer) {
        customer = customerService.updateCustomer(customer);
        if (customer == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Customer with customerNumber = " + customerNumber + " is not available"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/{customerNumber}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long customerNumber) {
        customerService.deleteCustomer(customerNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getAllCustomers(Pageable pageable) {
        Page<Customer> CustomerList = customerService.getAllCustomers(pageable);
        return new ResponseEntity<>(CustomerList, HttpStatus.OK);
    }

}
