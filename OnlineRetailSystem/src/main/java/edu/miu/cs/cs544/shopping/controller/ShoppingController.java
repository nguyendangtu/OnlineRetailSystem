package edu.miu.cs.cs544.shopping.controller;

import edu.miu.cs.cs544.product.domain.Product;
import edu.miu.cs.cs544.shopping.domain.ShoppingCart;
import edu.miu.cs.cs544.shopping.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : JOHNNGUYEN
 * @since : 5/20/2023, Sat
 **/
@RestController
@RequestMapping("/carts")
public class ShoppingController {

    private ShoppingService shoppingService;

    @Autowired
    public void setShoppingService(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @PostMapping("/{shoppingCartNumber}/product/{quantity}")
    public ResponseEntity<?> addProductToCart(@PathVariable("shoppingCartNumber") Long shoppingCartNumber, @PathVariable("quantity") int quantity, @RequestBody Product product) {
        ShoppingCart result = shoppingService.addProductToCart(shoppingCartNumber, quantity, product);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{shoppingCartNumber}")
    public ResponseEntity<?> getShoppingCart(@PathVariable Long shoppingCartNumber) {
        ShoppingCart shoppingCart = shoppingService.getShoppingCart(shoppingCartNumber);
        if (shoppingCart == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("ShoppingCart with shoppingCartNumber= "
                    + shoppingCartNumber + " is not available"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getAllShoppingCarts() {
        List<ShoppingCart> shoppingCartList = shoppingService.addAllShoppingCarts();
        return new ResponseEntity<>(shoppingCartList, HttpStatus.OK);
    }
}
