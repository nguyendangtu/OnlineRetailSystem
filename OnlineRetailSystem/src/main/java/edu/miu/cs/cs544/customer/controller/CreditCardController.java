package edu.miu.cs.cs544.customer.controller;

import edu.miu.cs.cs544.customer.domain.CreditCard;
import edu.miu.cs.cs544.customer.domain.Customer;
import edu.miu.cs.cs544.customer.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer/{customerId}/creditCard")
public class CreditCardController {

@Autowired
    CreditCardService creditCardService;

    public CreditCardService getCreditCardService() {
        return creditCardService;
    }

    @PostMapping
    public ResponseEntity<?> createCreditCard(@PathVariable Long customerID, @RequestBody CreditCard creditCard) {
            List<CreditCard> result = creditCardService.addCreditCard(customerID, creditCard);
            return new ResponseEntity<>(result, HttpStatus.OK);

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCreditcard(@PathVariable("id") Long creditCardNumber, @RequestBody CreditCard creditCard){
            creditCard = creditCardService.updateCreditCard(creditCardNumber, creditCard);
            if (creditCard == null) {
                return new ResponseEntity<CreditCard>(new CreditCard("CreditCards with CreditCards = " + creditCardNumber + " is not available"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(creditCard, HttpStatus.OK);
        }


    @GetMapping
    public ResponseEntity<?> getAllCreditCards(Pageable pageable) {
    Page<CreditCard> creditCardsList = creditCardService.getAllCreditCards(pageable);
    return new ResponseEntity<>(creditCardsList, HttpStatus.OK);
}
    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteCreditCard(@PathVariable("id") Long creditCardNumber){
        creditCardService.deleteCreditCard(creditCardNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
