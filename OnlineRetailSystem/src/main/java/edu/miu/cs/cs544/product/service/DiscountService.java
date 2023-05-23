package edu.miu.cs.cs544.product.service;

import edu.miu.cs.cs544.product.domain.Discount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiscountService {


    Discount addDiscount(Long productId, Discount percentageOfDiscount);

    Discount updateDiscount(Long discountId, Double percentageOfDiscount);

    Discount getDiscount(Long discountId);


}
