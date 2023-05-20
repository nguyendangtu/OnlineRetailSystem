package edu.miu.cs.cs544.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : JOHNNGUYEN
 * @since : 5/20/2023, Sat
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue
    private Long shoppingCartNumber;

    @OneToMany
    private List<CartLine> cartLines = new ArrayList();
}
