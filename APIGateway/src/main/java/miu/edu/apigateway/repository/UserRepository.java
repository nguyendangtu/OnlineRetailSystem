package miu.edu.apigateway.repository;

import miu.edu.apigateway.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : JOHNNGUYEN
 * @since : 5/22/2023, Mon
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserNameAndPassword(String userName, String password);

    User findByUserName(String userName);
}
