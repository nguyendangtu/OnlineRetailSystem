package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Product;
import edu.miu.cs.cs544.domain.ShoppingCart;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author : JOHNNGUYEN
 * @since : 5/20/2023, Sat
 **/
public interface ShoppingService {
    ShoppingCart addProductToCart(Long shoppingCartNumber, int quantity, @RequestBody Product product);

    ShoppingCart getShoppingCart(Long shoppingCartNumber);

    List<ShoppingCart> addAllShoppingCarts();
}
