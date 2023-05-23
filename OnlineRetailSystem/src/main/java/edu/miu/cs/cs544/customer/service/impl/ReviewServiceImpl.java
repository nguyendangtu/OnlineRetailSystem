package edu.miu.cs.cs544.customer.service.impl;


import edu.miu.cs.cs544.customer.domain.Customer;
import edu.miu.cs.cs544.customer.domain.Review;
import edu.miu.cs.cs544.customer.repository.CustomerRepository;
import edu.miu.cs.cs544.customer.repository.ReviewRepository;
import edu.miu.cs.cs544.customer.service.ReviewService;
import edu.miu.cs.cs544.product.domain.Product;
import edu.miu.cs.cs544.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Review getReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public Page<Review> getAllReviews(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }

    @Override
    public Review createReview(Long customerId, Review review) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null && review.getProduct() != null) {
            review.setCustomer(customer);
            Product product = productRepository.findById(review.getProduct().getId()).orElse(null);
            if (null != product) {
                review.setProduct(product);
            }
            return reviewRepository.save(review);
        }
        return null;
    }
}