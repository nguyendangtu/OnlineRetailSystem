package edu.miu.cs.cs544.customer.service;

import edu.miu.cs.cs544.customer.domain.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author : JOHNNGUYEN
 * @since : 5/21/2023, Sun
 **/
public interface AddressService {
    List<Address> addAddress(Long customerId, Address address);

    Address updateAddress(Address address);

    void deleteAddress(Long customerId, Long addressId);

    Address getAddress(Long addressId);

    Page<Address> getAllAddresses(Long customerId, Pageable pageable);
}
