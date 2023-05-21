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
 * @since : 5/20/2023, Sat
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartLine {
    @Id
    @GeneratedValue
    private Long id;

    private Integer quantity;

    @OneToOne
    private Product product;
}
