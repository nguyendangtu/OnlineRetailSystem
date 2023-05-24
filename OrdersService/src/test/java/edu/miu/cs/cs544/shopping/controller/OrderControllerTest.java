package edu.miu.cs.cs544.shopping.controller;

import edu.miu.cs.cs544.shopping.domain.Order;
import edu.miu.cs.cs544.shopping.domain.OrderStatus;
import edu.miu.cs.cs544.shopping.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author : JOHNNGUYEN
 * @since : 5/24/2023, Wed
 **/
@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    MockMvc mock;

    @MockBean
    OrderService orderService;

    @Test
    void createOrder() {
    }

    @Test
    void addItemToOrder() {
    }

    @Test
    void orderPayment() {
    }

    @Test
    void processShipping() {
    }

    @Test
    void returned() {
    }

    @Test
    void getOrders() {
    }

    @Test
    void getOrderDetails() throws Exception {
        Mockito.when(orderService.getOrderDetail(1l)).thenReturn(new Order(1l, OrderStatus.NEW, 10.0, 1l, 1l, null, null, null));
        mock.perform(MockMvcRequestBuilders.get("/orders/1")).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1l));
    }
}