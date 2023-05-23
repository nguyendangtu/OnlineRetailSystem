package edu.miu.cs.cs544.shopping.domain;

import edu.miu.cs.cs544.product.domain.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class OrderItem {

    @Id
    @GeneratedValue
    private Long id;

    private int quantity;

    private Double discountedPrice;

    private double totalPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    public void checkQuantity() {
        if (quantity > product.getQuantityInStock()) {
            throw new RuntimeException("The Quantity is more than the available stock");
        } else {
            int availableStock = product.getQuantityInStock() - quantity;
            product.setQuantityInStock(availableStock);
        }
    }


    public Double getDiscountedPrice() {
        if (product != null && product.getDiscount() != null) {
            this.discountedPrice = product.getPrice() - (product.getPrice() * product.getDiscount().getPercentageOfDiscount() / 100);
        }
        return discountedPrice;
    }

    public void setDiscountedPrice(Double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public double getTotalPrice() {
        this.totalPrice = quantity * discountedPrice;
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setTotalPrice() {
        this.totalPrice = quantity * discountedPrice;
    }

}
