package edu.miu.cs.cs544.customer.controller;

import edu.miu.cs.cs544.customer.domain.CreditCard;
import edu.miu.cs.cs544.customer.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer/{customerId}/credit-card")
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    @PostMapping
    public ResponseEntity<List<CreditCard>> createCreditCard(@PathVariable Long customerId, @RequestBody CreditCard creditCard) {
        List<CreditCard> result = creditCardService.addCreditCard(customerId, creditCard);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{creditCardNumber}")
    public ResponseEntity<CreditCard> updateCreditCard(@PathVariable Long customerId, @PathVariable Long creditCardNumber, @RequestBody CreditCard creditCard) {
        creditCard = creditCardService.updateCreditCard(creditCardNumber, creditCard);
        if (creditCard == null) {
            return new ResponseEntity<>(new CreditCard("CreditCards with CreditCards = " + creditCardNumber + " is not available"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(creditCard, HttpStatus.OK);
    }

    @GetMapping("/{creditCardNumber}")
    public ResponseEntity<CreditCard> getCreditCard(@PathVariable Long customerId, @PathVariable Long creditCardNumber) {
        CreditCard creditCard = creditCardService.getCreditCard(creditCardNumber);
        return new ResponseEntity<>(creditCard, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<CreditCard>> getAllCreditCards(@PathVariable Long customerId, Pageable pageable) {
        Page<CreditCard> creditCardsList = creditCardService.getAllCreditCards(pageable);
        return new ResponseEntity<>(creditCardsList, HttpStatus.OK);
    }

    @DeleteMapping("/{creditCardNumber}")
    public ResponseEntity<?> deleteCreditCard(@PathVariable Long customerId, @PathVariable Long creditCardNumber) {
        creditCardService.deleteCreditCard(creditCardNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
