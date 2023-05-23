package miu.edu.apigateway.service.impl;

import miu.edu.apigateway.domain.User;
import miu.edu.apigateway.repository.UserRepository;
import miu.edu.apigateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : JOHNNGUYEN
 * @since : 5/22/2023, Mon
 **/
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserByNameAndPassword(String name, String password) throws Exception {
        User user = userRepository.findByUserNameAndPassword(name, password);
        if (user == null) {
            throw new Exception("Invalid id and password");
        }
        return user;
    }
}