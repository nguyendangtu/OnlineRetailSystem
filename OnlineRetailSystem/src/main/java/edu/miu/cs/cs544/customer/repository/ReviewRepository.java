package edu.miu.cs.cs544.customer.repository;

import edu.miu.cs.cs544.customer.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
}
