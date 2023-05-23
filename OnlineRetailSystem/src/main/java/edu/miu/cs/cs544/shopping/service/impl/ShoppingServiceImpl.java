package edu.miu.cs.cs544.shopping.service.impl;

import edu.miu.cs.cs544.product.domain.Product;
import edu.miu.cs.cs544.shopping.domain.CartLine;
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
        CartLine cartLine = new CartLine();
        cartLine.setQuantity(quantity);
        cartLine.setProduct(product);
        ShoppingCart shoppingCart = shoppingRepository.findById(shoppingCartNumber).orElse(null);
        if (null == shoppingCart) {
            shoppingCart = new ShoppingCart();
        }

        CartLine exitingCardLine = shoppingCart.getCartLines().stream().filter(x -> x.getProduct().getId() == product.getId()).findFirst().orElse(null);
        if (null != exitingCardLine) {
            shoppingCart.getCartLines().remove(exitingCardLine);
            exitingCardLine.setQuantity(exitingCardLine.getQuantity() + quantity);
            shoppingCart.getCartLines().add(exitingCardLine);
        } else {
            shoppingCart.getCartLines().add(cartLine);
        }

        return shoppingRepository.save(shoppingCart);
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
