package edu.miu.cs.cs544.customer.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : JOHNNGUYEN
 * @since : 5/21/2023, Sun
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String contactNumber;

    @OneToOne
    @JoinColumn(name="billingAddressId")
    private Address billingAddress;

    @OneToMany
    @JoinColumn(name = "shippingAddressId")
    private List<Address> shippingAddress;

    @OneToMany
    @JoinColumn(name = "creditCardId")
    private List<CreditCard> creditCard;

}