package edu.miu.cs.cs544.customer.service.impl;

import edu.miu.cs.cs544.customer.domain.CreditCard;
import edu.miu.cs.cs544.customer.domain.Customer;
import edu.miu.cs.cs544.customer.repository.CreditCardRepository;
import edu.miu.cs.cs544.customer.repository.CustomerRepository;
import edu.miu.cs.cs544.customer.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CreditCardServiceImpl implements CreditCardService {
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public List<CreditCard> addCreditCard(Long customerId, CreditCard creditCard) {
     Customer customer =customerRepository.findById(customerId).orElse(null);
      if(customer!=null){
          customer.getCreditCard().add(creditCard);
        customer=customerRepository.save(customer);
        return customer.getCreditCard();
      }
      return null;
    }

    @Override
    public CreditCard updateCreditCard(long id, CreditCard creditCard) {
        CreditCard creditCard1 = creditCardRepository.findById(id).orElse(null);
        if (creditCard1 != null){
            creditCardRepository.save(creditCard1);
        }
        return null;
    }

    @Override
    public void deleteCreditCard(long id) {
        CreditCard creditCard = creditCardRepository.findById(id).orElse(null);
        creditCardRepository.delete(creditCard);
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

