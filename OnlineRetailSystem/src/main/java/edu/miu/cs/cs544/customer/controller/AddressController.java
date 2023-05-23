package edu.miu.cs.cs544.customer.controller;


import edu.miu.cs.cs544.customer.domain.Address;
import edu.miu.cs.cs544.customer.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : JOHNNGUYEN
 * @since : 5/21/2023, Sun
 **/
@RestController
@RequestMapping("/customer/{customerId}/address")
public class AddressController {

    private AddressService addressService;

    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

   /// @PostMapping
//    public ResponseEntity<?> createAddress(@PathVariable Long customerId, @RequestBody Address address) {
//        List<Address> result = addressService.addAddress(customerId, address);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

    @PostMapping("/billing")
    public ResponseEntity<?> createBillingAddress(@PathVariable Long customerId, @RequestBody Address address) {
        Address result = addressService.addBillingAddress(customerId, address);
        if(result != null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("customer with ID" + customerId + " do not exist");
    }

    @PostMapping("/shipping")
    public ResponseEntity<?> createShippingAddress(@PathVariable Long customerId, @RequestBody Address address) {
        List<Address> addressList = addressService.addShippingAddress(customerId, address);
        if(addressList != null)
            return ResponseEntity.ok().body(addressList);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("customer with ID" + customerId + " do not exist");
    }

    @GetMapping("/{addressNumber}")
    public ResponseEntity<?> getAddress(@PathVariable Long customerId, @PathVariable Long addressNumber) {
        Address address = addressService.getAddress(addressNumber);
        if (address == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Address with addressNumber= " + addressNumber + " is not available"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @PutMapping("/{AddressNumber}")
    public ResponseEntity<?> updateAddress(@PathVariable Long customerId, @PathVariable Long AddressNumber, @RequestBody Address address) {
        address = addressService.updateAddress(customerId,address);
        if (address == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Address with AddressNumber = " + AddressNumber + " is not available"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @DeleteMapping("/{AddressNumber}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long customerId, @PathVariable Long addressNumber) {
        addressService.deleteAddress(customerId, addressNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getAllAddresses(@PathVariable Long customerId, Pageable pageable) {
        Page<Address> AddressList = addressService.getAllAddresses(customerId, pageable);
        return new ResponseEntity<>(AddressList, HttpStatus.OK);
    }
}
