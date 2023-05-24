package edu.miu.cs.cs544.customer.service.impl;


import edu.miu.cs.cs544.customer.domain.Customer;
import edu.miu.cs.cs544.customer.domain.Product;
import edu.miu.cs.cs544.customer.domain.Review;
import edu.miu.cs.cs544.customer.repository.CustomerRepository;
import edu.miu.cs.cs544.customer.repository.ReviewRepository;
import edu.miu.cs.cs544.customer.service.ReviewService;
import edu.miu.cs.cs544.feignclient.ProductFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Override
    public Review getReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null) {
            review.setProduct(productFeignClient.getProduct(review.getProductId()).getBody());
        }
        return review;
    }

    @Override
    public Page<Review> getAllReviews(Pageable pageable) {
        Page<Review> reviewPage = reviewRepository.findAll(pageable);
        if (reviewPage != null) {
            reviewPage.forEach(i -> i.setProduct(productFeignClient.getProduct(i.getProductId()).getBody()));
        }
        return reviewPage;
    }

    @Override
    public Review createReview(Long customerId, Review review) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null && review.getProductId() != null) {
            review.setCustomer(customer);
            ResponseEntity<Product> responseEntity = productFeignClient.getProduct(review.getProductId());
            if (null != responseEntity.getBody()) {
                review.setProductId(responseEntity.getBody().getId());
            }
            Review result = reviewRepository.save(review);
            result.setProduct(responseEntity.getBody());
            return result;
        }
        return null;
    }
}