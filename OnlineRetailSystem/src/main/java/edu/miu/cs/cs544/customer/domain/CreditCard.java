package edu.miu.cs.cs544.customer.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author : JOHNNGUYEN
 * @since : 5/21/2023, Sun
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CreditCard {
    @Id
    @GeneratedValue
    private Long id;

    private String creditCardNumber;
    private LocalDate expirationDate;
    private String securityCode;

    /*@ManyToOne
    private Customer customer;*/

    public CreditCard(String s) {
    }

    public CreditCard(String creditCardNumber, LocalDate expirationDate, String securityCode) {
        this.creditCardNumber = creditCardNumber;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
    }
}
