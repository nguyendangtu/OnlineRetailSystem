package edu.miu.cs.cs544.customer.domain;

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
public class Discount {

    private Long id;

    private Double percentageOfDiscount;

    public Discount(Double percentageOfDiscount) {
        this.percentageOfDiscount = percentageOfDiscount;
    }
}
