package edu.miu.cs.cs544.customer.service.impl;

import edu.miu.cs.cs544.customer.domain.CreditCard;
import edu.miu.cs.cs544.customer.repository.CreditCardRepository;
import edu.miu.cs.cs544.customer.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class CreditCardServiceImpl implements CreditCardService {
    @Autowired
    private CreditCardRepository creditCardRepository;

    @Override
    public CreditCard addCreditCard(CreditCard creditCard) {

        return creditCardRepository.save(creditCard);
    }

    @Override
    public CreditCard updateCreditCard(CreditCard creditCard) {
        return null;
    }

    @Override
    public void deleteCreditCard(long id) {

    }

    @Override
    public List<CreditCard> getCreditCard() {
        List<CreditCard> creditCardList = creditCardRepository.findAll();
        return creditCardList;
    }

    @Override
    public Page<CreditCard> getAllCreditCards(Pageable pageable) {

            return creditCardRepository.findAll(pageable);
        }
    }

