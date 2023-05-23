package edu.miu.cs.cs544.customer.service.impl;


import edu.miu.cs.cs544.customer.domain.Review;
import edu.miu.cs.cs544.customer.repository.ReviewRepository;
import edu.miu.cs.cs544.customer.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Review getReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public Page<Review> getAllReviews(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }

    @Override
    public Review createReview(Review review) {
        return null;
    }
}