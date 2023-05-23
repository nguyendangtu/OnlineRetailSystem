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
    CreditCardService creditCardService;

    public CreditCardService getCreditCardService() {
        return creditCardService;
    }

    @PostMapping
    public ResponseEntity<?> createCreditCard(@PathVariable Long customerId, @RequestBody CreditCard creditCard) {
        List<CreditCard> result = creditCardService.addCreditCard(customerId, creditCard);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @PutMapping("/{creditCardNumber}")
    public ResponseEntity<?> updateCreditCard(@PathVariable Long creditCardNumber, @RequestBody CreditCard creditCard) {
        creditCard = creditCardService.updateCreditCard(creditCardNumber, creditCard);
        if (creditCard == null) {
            return new ResponseEntity<CreditCard>(new CreditCard("CreditCards with CreditCards = " + creditCardNumber + " is not available"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(creditCard, HttpStatus.OK);
    }

    @GetMapping("/{creditCardNumber}")
    public ResponseEntity<?> getCreditCard(@PathVariable Long creditCardNumber) {
        CreditCard creditCard = creditCardService.getCreditCard(creditCardNumber);
        return new ResponseEntity<>(creditCard, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllCreditCards(Pageable pageable) {
        Page<CreditCard> creditCardsList = creditCardService.getAllCreditCards(pageable);
        return new ResponseEntity<>(creditCardsList, HttpStatus.OK);
    }

    @DeleteMapping("/{creditCardNumber}")
    public ResponseEntity<?> deleteCreditCard(@PathVariable Long creditCardNumber) {
        creditCardService.deleteCreditCard(creditCardNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
