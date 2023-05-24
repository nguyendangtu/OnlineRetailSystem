package edu.miu.cs.cs544.product.service.impl;

import edu.miu.cs.cs544.product.domain.Discount;
import edu.miu.cs.cs544.product.domain.Product;
import edu.miu.cs.cs544.product.repository.DiscountRepository;
import edu.miu.cs.cs544.product.repository.ProductRepository;
import edu.miu.cs.cs544.product.service.DiscountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService {

    private DiscountRepository discountrepository;

    private ProductRepository productRepository;

    @Autowired
    public void setDiscount(DiscountRepository discountrepository) {
        this.discountrepository = discountrepository;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Discount addDiscount(Long producId, Discount discount) {

        Product product = productRepository.getById(producId);
        if (product != null && product.getDiscount() == null) {
            Discount newDiscount = discountrepository.save(discount);
            product.setDiscount(newDiscount);
            return productRepository.save(product).getDiscount();
        }
        return null;
    }

    @Override
    public Discount updateDiscount(Long discountId, Double percentageOfDiscount) {
        Discount discount = discountrepository.findById(discountId).orElse(null);
        if (null != discount) {
            discount.setPercentageOfDiscount(percentageOfDiscount);
            return discountrepository.save(discount);
        }
        return null;
    }


    @Override
    public Discount getDiscount(Long discountId) {
        return discountrepository.findById(discountId).orElse(null);
    }


}