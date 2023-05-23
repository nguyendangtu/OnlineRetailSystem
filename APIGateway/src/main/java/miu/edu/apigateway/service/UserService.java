package miu.edu.apigateway.service;

import miu.edu.apigateway.domain.User;

/**
 * @author : JOHNNGUYEN
 * @since : 5/22/2023, Mon
 **/
public interface UserService {

    void saveUser(User user);

    User getUserByNameAndPassword(String name, String password) throws Exception;
}
