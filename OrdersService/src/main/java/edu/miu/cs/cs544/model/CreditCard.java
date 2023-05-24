package edu.miu.cs.cs544.model;

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
public class CreditCard {

    private Long id;

    private String creditCardNumber;

    private String expirationDate;
    private String securityCode;


    public CreditCard(String s) {
    }

    public CreditCard(String creditCardNumber, String expirationDate, String securityCode) {
        this.creditCardNumber = creditCardNumber;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
    }
}
