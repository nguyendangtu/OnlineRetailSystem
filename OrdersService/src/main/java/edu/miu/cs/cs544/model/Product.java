package edu.miu.cs.cs544.model;

import edu.miu.cs.cs544.model.Discount;
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
public class Product {

    private Long id;

    private String name;
    private Double price;
    private String image;
    private String barcodeNumber;
    private Integer quantityInStock;

    private Discount discount;

    public Product(String name, Double price, String image, String barcodeNumber, Integer quantityInStock) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.barcodeNumber = barcodeNumber;
        this.quantityInStock = quantityInStock;
    }


}
