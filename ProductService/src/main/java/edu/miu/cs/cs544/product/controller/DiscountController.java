package edu.miu.cs.cs544.product.controller;

import edu.miu.cs.cs544.product.domain.Discount;
import edu.miu.cs.cs544.product.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product/{productId}/discount")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @Autowired
    public void setDiscountService(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping
    public ResponseEntity<?> createDiscount(@PathVariable Long productId, @RequestBody Discount discount) {
        Discount result = discountService.addDiscount(productId, discount);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{discountId}")
    public ResponseEntity<?> getDiscount(@PathVariable Long productId, @PathVariable Long discountId) {
        Discount discount = discountService.getDiscount(discountId);
        if (discount == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Product with  = " + productId + " is not have discount"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(discount, HttpStatus.OK);
    }

    @PutMapping("/{discountId}")
    public ResponseEntity<?> updateDiscount(@PathVariable Long productId, @PathVariable Long discountId, @RequestBody Discount discount) {
        discount = discountService.updateDiscount(discountId, discount.getPercentageOfDiscount());
        if (discount == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("This product: " + productId + " doesn't has discount "), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(discount, HttpStatus.OK);
    }


}
