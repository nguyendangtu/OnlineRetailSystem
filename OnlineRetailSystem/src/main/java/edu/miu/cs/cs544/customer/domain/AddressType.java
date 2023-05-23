package edu.miu.cs.cs544.customer.domain;

import edu.miu.cs.cs544.customer.enums.CustomerAddressType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class AddressType {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

   private CustomerAddressType customerAddressType;
}
