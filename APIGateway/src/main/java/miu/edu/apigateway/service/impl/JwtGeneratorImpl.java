package miu.edu.apigateway.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import miu.edu.apigateway.domain.User;
import miu.edu.apigateway.service.JwtGeneratorInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : JOHNNGUYEN
 * @since : 5/22/2023, Mon
 **/
@Service
public class JwtGeneratorImpl implements JwtGeneratorInterface {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.message}")
    private String message;

    @Override
    public Map<String, String> generateToken(User user) {
        String jwtToken = "";
        jwtToken = Jwts.builder().setSubject(user.getUsername()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secret").compact();
        Map<String, String> jwtTokenGen = new HashMap<>();
        jwtTokenGen.put("token", jwtToken);
        jwtTokenGen.put("message", message);
        return jwtTokenGen;
    }
}
