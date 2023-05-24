package edu.miu.cs.cs544.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : JOHNNGUYEN
 * @since : 5/21/2023, Sun
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String contactNumber;

    private Address billingAddress;

    private List<Address> shippingAddress = new ArrayList<>();

    private List<CreditCard> creditCard = new ArrayList<>();

    public Customer(String firstName, String lastName, String email, String contactNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNumber = contactNumber;
    }
}
