package edu.miu.cs.cs544.customer.service.impl;


import edu.miu.cs.cs544.customer.domain.Address;
import edu.miu.cs.cs544.customer.domain.Customer;
import edu.miu.cs.cs544.customer.repository.AddressRepository;
import edu.miu.cs.cs544.customer.repository.CustomerRepository;
import edu.miu.cs.cs544.customer.service.AddressService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
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
@Transactional
public class AddressServiceImpl implements AddressService {

   private ModelMapper modelMapper;
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
    @Autowired

    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Address> addShippingAddress(Long customerId, Address address) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer!=null) {
            customer.getShippingAddress().add(address);
            customer = customerRepository.save(customer);
            return customer.getShippingAddress();
        }
        return null;
    }
    @Override
    public Address addBillingAddress(Long customerId, Address address) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer!=null) {
            Address newAddress = addressRepository.save(address);
            customer.setBillingAddress(newAddress);
            customerRepository.save(customer);
            return newAddress;

        }
        return null;
    }


    @Override
    public Address updateAddress(Long addressId, Address address) {

        Address updateAddress = addressRepository.findById(addressId).orElse(null);
        if (updateAddress != null) {
            updateAddress.setCity(address.getCity());
            updateAddress.setStreet(address.getStreet());
            updateAddress.setState(address.getState());
            updateAddress.setCountry(address.getCountry());
            updateAddress.setZipCode(address.getZipCode());
            return addressRepository.save(address);
        }
        return null;
    }
    @Override
    public void deleteAddress(Long customerId, Long addressId) {
        Address address = addressRepository.findById(addressId).orElse(null);
        //check address is from billing or shipping. if from billing, we can not delete.
        // If address from shipping, we can delete from customer
        //todo

//        if (address.getCustomerAddressType().type() == "SHIPPING_ADDRESS") {
//            addressRepository.deleteById(addressId);
//        }
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

        return addressRepository.findAll(pageable)
                .map(entity->modelMapper.map(entity, Address.class));


    }

//    public Boolean isShippingAddress(Address address) {
//        return "SHIPPING_ADDRESS".equalsIgnoreCase(address.getAddressType().getName());
//    }
//    public Boolean isBillingAddress(Address address) {
//        return "BILLING_ADDRESS".equalsIgnoreCase(address.getAddressType().getName());
//    }
}
