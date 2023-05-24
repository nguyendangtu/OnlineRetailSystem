package edu.miu.cs.cs544.customer.service;


import edu.miu.cs.cs544.customer.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {

    Review createReview(Long customerId, Review review);

    Review getReview(Long reviewId);

    Page<Review> getAllReviews(Pageable pageable);

}
