package miu.edu.apigateway.repository;

import miu.edu.apigateway.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : JOHNNGUYEN
 * @since : 5/22/2023, Mon
 **/
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByName(String name);
}
