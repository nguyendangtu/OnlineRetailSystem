package edu.miu.cs.cs544.customer.service.impl;


import edu.miu.cs.cs544.customer.domain.Address;
import edu.miu.cs.cs544.customer.domain.Customer;
import edu.miu.cs.cs544.customer.repository.AddressRepository;
import edu.miu.cs.cs544.customer.repository.CustomerRepository;
import edu.miu.cs.cs544.customer.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : JOHNNGUYEN
 * @since : 5/21/2023, Sun
 **/
@Service
public class AddressServiceImpl implements AddressService {


    private CustomerRepository customerRepository;

    private AddressRepository addressRepository;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Autowired
    public void setAddressRepository(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> addAddress(Long customerId, Address address) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            if (isShippingAddress(address)) {
                customer.getShippingAddress().add(address);
                customer = customerRepository.save(customer);
                return customer.getShippingAddress();
            }
        }
        return null;
    }

    @Override
    public Address updateAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void deleteAddress(Long customerId, Long addressId) {
        Address address = addressRepository.findById(addressId).orElse(null);
        //check address is from billing or shipping. if from billing, we can not delete.
        // If address from shipping, we can delete from customer
        //todo

        addressRepository.deleteById(addressId);
    }

    @Override
    public Address getAddress(Long addressId) {
        return addressRepository.findById(addressId).orElse(null);
    }

    @Override
    public Page<Address> getAllAddresses(Long customerId, Pageable pageable) {
        //todo
        //get all address from customer
        //filter address for pageable
        return null;

    }

    public Boolean isShippingAddress(Address address) {
        return "SHIPPING_ADDRESS".equalsIgnoreCase(address.getAddressType().getName());
    }
}
