package edu.miu.cs.cs544.shopping.service.impl;

import edu.miu.cs.cs544.product.domain.Product;
import edu.miu.cs.cs544.shopping.domain.ShoppingCart;
import edu.miu.cs.cs544.shopping.repository.ShoppingRepository;
import edu.miu.cs.cs544.shopping.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : JOHNNGUYEN
 * @since : 5/20/2023, Sat
 **/
@Service
public class ShoppingServiceImpl implements ShoppingService {

    private ShoppingRepository shoppingRepository;

    @Autowired
    public void setShoppingRepository(ShoppingRepository shoppingRepository) {
        this.shoppingRepository = shoppingRepository;
    }

    @Override
    public ShoppingCart addProductToCart(Long shoppingCartNumber, int quantity, Product product) {
        //TODO
        return null;
    }

    @Override
    public ShoppingCart getShoppingCart(Long shoppingCartNumber) {
        return shoppingRepository.findById(shoppingCartNumber).orElse(null);
    }

    @Override
    public List<ShoppingCart> addAllShoppingCarts() {
        return shoppingRepository.findAll();
    }
}
