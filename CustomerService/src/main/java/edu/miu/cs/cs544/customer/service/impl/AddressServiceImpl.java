package edu.miu.cs.cs544.customer.service.impl;


import edu.miu.cs.cs544.customer.domain.Address;
import edu.miu.cs.cs544.customer.domain.Customer;
import edu.miu.cs.cs544.customer.enums.AddressType;
import edu.miu.cs.cs544.customer.repository.AddressRepository;
import edu.miu.cs.cs544.customer.repository.CustomerRepository;
import edu.miu.cs.cs544.customer.service.AddressService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
        if (customer != null) {
            address.setAddressType(AddressType.SHIPPING_ADDRESS);
            address = addressRepository.save(address);
            customer.getShippingAddress().add(address);
            customer = customerRepository.save(customer);

            return customer.getShippingAddress();
        }
        return null;
    }

    @Override
    public Address addBillingAddress(Long customerId, Address address) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null && customer.getBillingAddress() == null) {
            address.setAddressType(AddressType.BILLING_ADDRESS);
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
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null && address != null) {
            if (isShippingAddress(address)) {
                customer.setShippingAddress(customer.getShippingAddress().stream().filter(a -> a.getId() != addressId).toList());
                addressRepository.deleteById(addressId);
            }
        }
    }

    @Override
    public Address getAddress(Long addressId) {
        return addressRepository.findById(addressId).orElse(null);
    }

    @Override
    public Page<Address> getAllAddresses(Long customerId, Pageable pageable) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (null != customer) {
            List<Address> addresses = customer.getShippingAddress();
            addresses.add(customer.getBillingAddress());
            return new PageImpl<>(addresses);
        }
        return null;
    }

    public Boolean isShippingAddress(Address address) {
        return "SHIPPING_ADDRESS".equalsIgnoreCase(address.getAddressType().getType());
    }
}
