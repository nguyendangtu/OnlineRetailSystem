package miu.edu.apigateway.controller;

import miu.edu.apigateway.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author : JOHNNGUYEN
 * @since : 5/24/2023, Wed
 **/
@RunWith(SpringRunner.class)
class UserControllerTest {

    @Autowired
    MockMvc mock;

    @MockBean
    UserService userService;

    @Test
    void createUser() {
    }

    @Test
    void loginUser() {
    }
}