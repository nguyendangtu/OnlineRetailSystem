package miu.edu.apigateway.controller;

import miu.edu.apigateway.service.JwtGeneratorInterface;
import miu.edu.apigateway.domain.User;
import miu.edu.apigateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : JOHNNGUYEN
 * @since : 5/22/2023, Mon
 **/
@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private UserService userService;
    private JwtGeneratorInterface jwtGenerator;

    @Autowired
    public UserController(UserService userService, JwtGeneratorInterface jwtGenerator) {
        this.userService = userService;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> postUser(@RequestBody User user) {
        try {
            userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            if (user.getUserName() == null || user.getPassword() == null) {
                throw new Exception("UserName or Password is Empty");
            }
            User userData = userService.getUserByNameAndPassword(user.getUserName(), user.getPassword());
            if (userData == null) {
                throw new Exception("UserName or Password is Invalid");
            }
            return new ResponseEntity<>(jwtGenerator.generateToken(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
