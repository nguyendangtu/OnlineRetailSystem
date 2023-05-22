package edu.miu.cs.cs544.customer.service;

import edu.miu.cs.cs544.customer.domain.Address;
import edu.miu.cs.cs544.customer.domain.CreditCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CreditCardService {
    CreditCard addCreditCard(CreditCard creditCard);

    CreditCard updateCreditCard(CreditCard creditCard);

    void deleteCreditCard(long id);
    List<CreditCard> getCreditCard();

    Page<CreditCard> getAllCreditCards(Pageable pageable);
}
