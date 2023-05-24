package edu.miu.cs.cs544.shopping.service.impl;

import edu.miu.cs.cs544.feignclient.CustomerFeignClient;
import edu.miu.cs.cs544.feignclient.ProductFeignClient;
import edu.miu.cs.cs544.model.CreditCard;
import edu.miu.cs.cs544.model.Customer;
import edu.miu.cs.cs544.model.Product;
import edu.miu.cs.cs544.shopping.domain.Order;
import edu.miu.cs.cs544.shopping.domain.OrderItem;
import edu.miu.cs.cs544.shopping.domain.OrderStatus;
import edu.miu.cs.cs544.shopping.repository.OrderRepository;
import edu.miu.cs.cs544.shopping.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static edu.miu.cs.cs544.shopping.domain.OrderStatus.NEW;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerFeignClient customerFeignClient;

    @Mock
    private ProductFeignClient productFeignClient;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    public void testCreateOrder() {
        // Prepare test data
        Order order = new Order();
        order.setCustomerId(1L); // Set a valid customer ID
        // Set other necessary order properties

        Customer customer = new Customer();
        customer.setId(1L); // Set the same customer ID as in the order
        // Set other necessary customer properties

        Product product = new Product();
        product.setId(1L); // Set a valid product ID
        // Set other necessary product properties

        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(1L); // Set the same product ID as in the product
        // Set other necessary order item properties

        // Mock the behavior of the customerFeignClient and productFeignClient
        Mockito.when(customerFeignClient.getCustomer(Mockito.anyLong())).thenReturn(ResponseEntity.ok(customer));
//        Mockito.when(productFeignClient.getProduct(Mockito.anyLong())).thenReturn(ResponseEntity.ok(product));

        // Mock the behavior of the orderRepository
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);

        // Call the createOrder method
        Order result = orderService.createOrder(order);

        // Verify that the orderRepository.save method was called once
        Mockito.verify(orderRepository, Mockito.times(1)).save(Mockito.any(Order.class));

        // Verify that the customerFeignClient.getCustomer method was called once
        Mockito.verify(customerFeignClient, Mockito.times(1)).getCustomer(Mockito.anyLong());

        // Verify that the productFeignClient.getProduct method was called once
//        Mockito.verify(productFeignClient, Mockito.times(1)).getProduct(Mockito.anyLong());

        // Further assertions or verifications based on the expected behavior
        assertNotNull(result);
        assertEquals(NEW, result.getOrderStatus());
        assertEquals(customer, result.getCustomer());
        // Verify other expected values in the result object
    }

    @Test
    public void testAddItemToOrder() throws OrderNotFoundException {
        // Prepare test data
        long orderId = 1L; // Existing order ID
        Order order = new Order();
        // Set other necessary order properties

        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(1L); // Existing product ID
        // Set other necessary order item properties
        order.setOrderStatus(OrderStatus.NEW); // Set a non-null order status
        // Mock the behavior of the orderRepository
        Mockito.when(orderRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(order));
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);

        // Mock the behavior of the customerFeignClient
        Customer customer = new Customer();
        customer.setId(1L); // Set the same customer ID as in the order
//        Mockito.when(customerFeignClient.getCustomer(Mockito.anyLong())).thenReturn(ResponseEntity.ok(customer));

        // Mock the behavior of the productFeignClient
        Product product = new Product();
        product.setId(1L); // Set the same product ID as in the order item
        product.setName("Sample Product");
        Mockito.when(productFeignClient.getProduct(Mockito.anyLong())).thenReturn(ResponseEntity.ok(new Product()));

        Mockito.when(customerFeignClient.getCustomer(Mockito.eq(order.getCustomerId()))).thenReturn(ResponseEntity.ok(customer));

        // Call the addItemToOrder method
        Order result = orderService.addItemToOrder(orderId, orderItem);

        // Verify that the orderRepository.findById method was called once with the correct orderId
        Mockito.verify(orderRepository, Mockito.times(1)).findById(orderId);

        // Verify that the orderRepository.save method was called once
        Mockito.verify(orderRepository, Mockito.times(1)).save(Mockito.any(Order.class));

        // Verify that the customerFeignClient.getCustomer method was called once with the correct customerId
        Mockito.verify(customerFeignClient, Mockito.times(1)).getCustomer(Mockito.eq(order.getCustomerId()));

        // Verify that the productFeignClient.getProduct method was called once with the correct productId
//        Mockito.verify(productFeignClient, Mockito.times(1)).getProduct(Mockito.eq(orderItem.getProductId()));

        // Further assertions or verifications based on the expected behavior
        assertNotNull(result);
        // Verify the updated order with the added item, customer, and product details
        // Verify other expected values in the result object
    }

    @Test
    public void testAddItemToOrder_OrderNotFound() throws OrderNotFoundException {
        long orderId = 1L; // Non-existent order ID
        OrderItem orderItem = new OrderItem();
        // Set necessary order item properties

        // Mock the behavior of the orderRepository
        Mockito.when(orderRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        // Call the addItemToOrder method, expecting an OrderNotFoundException
        orderService.addItemToOrder(orderId, orderItem);
    }

    @Test
    public void testOrderPayment() throws OrderNotFoundException {
        // Prepare test data
        long orderId = 1L; // Existing order ID
        CreditCard creditCard = new CreditCard();
        creditCard.setCreditCardNumber("123456789"); // Credit card number to match

        Order order = new Order();
        order.setId(orderId);
        order.setCustomerId(1L); // Set the customer ID
        // Set other necessary order properties

        Customer customer = new Customer();
        customer.setId(1L); // Set the same customer ID as in the order
        List<CreditCard> cards = new ArrayList<>();
        CreditCard matchingCard = new CreditCard();
        matchingCard.setCreditCardNumber("123456789"); // Set the same credit card number
        // Set other necessary credit card properties
        cards.add(matchingCard);
        customer.setCreditCard(cards);

        // Mock the behavior of the orderRepository
        Mockito.lenient().when(orderRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(order));
        Mockito.lenient().when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);

        // Mock the behavior of the customerFeignClient
        Mockito.lenient().when(customerFeignClient.getCustomer(Mockito.eq(order.getCustomerId()))).thenReturn(ResponseEntity.ok(customer));
        // Mock the behavior of the productFeignClient
        Product product = new Product();
        product.setId(1L); // Set the product ID used in the order
        // Set other necessary product properties
        Mockito.lenient().when(productFeignClient.getProduct(Mockito.anyLong())).thenReturn(ResponseEntity.ok(product));
        // Call the orderPayment method
        Order result = orderService.orderPayment(orderId, creditCard);

        // Verify that the orderRepository.findById method was called once with the correct orderId
        Mockito.verify(orderRepository, Mockito.times(1)).findById(orderId);

        // Verify that the orderRepository.save method was called once
        Mockito.verify(orderRepository, Mockito.times(1)).save(Mockito.any(Order.class));

        // Verify that the customerFeignClient.getCustomer method was called once with the correct customerId
        Mockito.verify(customerFeignClient, Mockito.times(1)).getCustomer(Mockito.eq(order.getCustomerId()));

        // Verify that the productFeignClient.getProduct method was called for each order item
        Mockito.verify(productFeignClient, Mockito.times(order.getOrderItems().size())).getProduct(Mockito.anyLong());

        // Further assertions or verifications based on the expected behavior
        assertNotNull(result);
        // Verify the updated order with the payment method, customer, and product details
        // Verify other expected values in the result object
    }


}