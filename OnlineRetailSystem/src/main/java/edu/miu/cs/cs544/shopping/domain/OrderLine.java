package edu.miu.cs.cs544.shopping.domain;

import edu.miu.cs.cs544.product.domain.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : JOHNNGUYEN
 * @since : 5/21/2023, Sun
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderLine {

    @Id
    @GeneratedValue
    private Long id;

    private Integer quantity;

    private Double discountedPrice;

    @OneToOne
    private Product product;

    public void checkProductDiscount() {
        if (product != null && product.getDiscount() != null) {
            this.discountedPrice = product.getPrice() - (product.getPrice() * product.getDiscount().getPercentageOfDiscount() / 100);
            System.out.println("Discounted price for product " + product.getName() + ": " + discountedPrice);
        }
    }
}
