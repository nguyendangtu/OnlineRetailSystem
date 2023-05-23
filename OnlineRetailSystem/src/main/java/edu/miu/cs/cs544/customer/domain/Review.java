package edu.miu.cs.cs544.customer.domain;

import edu.miu.cs.cs544.product.domain.Product;
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
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;
    private Integer numberOfStars;
    private String date;

    @OneToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "productId")
    private Product product;

    public Review(String title, String description, Integer numberOfStars, String date) {
        this.title = title;
        this.description = description;
        this.numberOfStars = numberOfStars;
        this.date = date;
    }
}
