package edu.miu.cs.cs544.customer.domain;

import edu.miu.cs.cs544.customer.enums.AddressType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : JOHNNGUYEN
 * @since : 5/21/2023, Sun
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    private String street;
    private String city;
    private String state;
    private String ZipCode;
    private String country;

    private AddressType addressType;

    public Address(String street, String city, String state, String zipCode, String country, AddressType addressType) {
        this.street = street;
        this.city = city;
        this.state = state;
        ZipCode = zipCode;
        this.country = country;
        this.addressType = addressType;
    }
}
