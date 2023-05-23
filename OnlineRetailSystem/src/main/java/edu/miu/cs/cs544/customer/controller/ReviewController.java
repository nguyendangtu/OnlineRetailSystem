package edu.miu.cs.cs544.customer.controller;

import edu.miu.cs.cs544.customer.domain.Review;
import edu.miu.cs.cs544.customer.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer/{customerId}/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping
    public ResponseEntity<?> createReview(@PathVariable Long customerId, @RequestBody Review review) {
        Review result = reviewService.createReview(customerId, review);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<?> getReview(@PathVariable Long reviewId) {
        Review review = reviewService.getReview(reviewId);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllReviews(Pageable pageable) {
        Page<Review> reviewList = reviewService.getAllReviews(pageable);
        return new ResponseEntity<>(reviewList, HttpStatus.OK);
    }


}