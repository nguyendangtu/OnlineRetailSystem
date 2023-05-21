package edu.miu.cs.cs544.customer.domain;

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

    @ManyToOne
    @JoinColumn(name = "addressTypeId")
    private AddressType addressType;

}
