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
@RequestMapping("/customers/{customerId}/creditCard")
public class CreditCardController {

@Autowired
    CreditCardService creditCardService;

    public CreditCardService getCreditCardService() {
        return creditCardService;
    }

    @GetMapping()
    public ResponseEntity<?> createCreditCard(@RequestBody CreditCard creditCard) {
            CreditCard result =creditCardService.addCreditCard(creditCard);
            return new ResponseEntity<>(result, HttpStatus.OK);

    }
    @GetMapping()
    public ResponseEntity<?> updateCreditcard(@PathVariable Long creditCardNumber, @RequestBody CreditCard creditCard){
            creditCard = creditCardService.updateCreditCard(creditCard);
            if (creditCard == null) {
                return new ResponseEntity<CreditCard>(new CreditCard("CreditCards with CreditCards = " + creditCardNumber + " is not available"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(creditCard, HttpStatus.OK);
        }


    @GetMapping()
    public  ResponseEntity<?> deleteCreditCard(@PathVariable Long creditCardNumber){
        creditCardService.deleteCreditCard(creditCardNumber);
        return new ResponseEntity<>(HttpStatus.OK);
}

public ResponseEntity<?> getAllCreditCards(Pageable pageable) {
    Page<CreditCard> creditCardsList = creditCardService.getAllCreditCards(pageable);
    return new ResponseEntity<>(creditCardsList, HttpStatus.OK);
}
}
