package miu.edu.apigateway.service;

import miu.edu.apigateway.domain.User;

import java.util.Map;

/**
 * @author : JOHNNGUYEN
 * @since : 5/22/2023, Mon
 **/
public interface JwtGeneratorInterface {
    Map<String, String> generateToken(User user);
}
