package edu.miu.cs.cs544.product.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : JOHNNGUYEN
 * @since : 5/20/2023, Sat
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Double price;
    private String image;
    private String barcodeNumber;
    private Integer quantityInStock;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "discountId")
    private Discount discount;

    public Product(String name, Double price, String image, String barcodeNumber, Integer quantityInStock) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.barcodeNumber = barcodeNumber;
        this.quantityInStock = quantityInStock;
    }

}
