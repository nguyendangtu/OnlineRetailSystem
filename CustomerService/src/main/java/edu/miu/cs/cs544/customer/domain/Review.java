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
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;
    private Integer numberOfStars;
    private String date;
    private Long productId;

    @Transient
    private Product product;

    @OneToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    public Review(String title, String description, Integer numberOfStars, String date, Long productId) {
        this.title = title;
        this.description = description;
        this.numberOfStars = numberOfStars;
        this.date = date;
        this.productId = productId;
    }
}
