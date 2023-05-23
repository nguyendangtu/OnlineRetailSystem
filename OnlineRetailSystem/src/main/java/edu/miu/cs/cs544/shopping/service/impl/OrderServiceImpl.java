package edu.miu.cs.cs544.shopping.service.impl;

import edu.miu.cs.cs544.customer.domain.CreditCard;
import edu.miu.cs.cs544.customer.domain.Customer;
import edu.miu.cs.cs544.customer.repository.CustomerRepository;
import edu.miu.cs.cs544.customer.service.CustomerService;
import edu.miu.cs.cs544.product.repository.ProductRepository;
import edu.miu.cs.cs544.shopping.domain.Order;
import edu.miu.cs.cs544.shopping.domain.OrderItem;
import edu.miu.cs.cs544.shopping.domain.OrderStatus;
import edu.miu.cs.cs544.shopping.repository.OrderItemRepository;
import edu.miu.cs.cs544.shopping.repository.OrderRepository;
import edu.miu.cs.cs544.shopping.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static edu.miu.cs.cs544.shopping.domain.OrderStatus.*;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    CustomerService customerService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderItemRepository orderItemRepository;


    @Override
    public List<Order> getAllOrders(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public Order getOrderDetail(long orderId) {
        return this.orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public Order createOrder(Order order) {
        if (order.getCustomer() == null) {
            return null;
        }

        Customer customer = customerRepository.findById(order.getCustomer().getId()).orElse(null);
        if (customer == null) {
            return null;
        } else {
            order.setCustomer(customer);
            order.setOrderStatus(NEW);

            return orderRepository.save(order);
        }
    }

    @Override
    public Order addItemToOrder(long orderId, OrderItem orderItem) throws OrderNotFoundException {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.getOrderItems().add(orderItem);
            return orderRepository.save(order);
        } else {
            throw new OrderNotFoundException("Order not found with orderId" + orderId);
        }
    }

    @Override
    public Order orderPayment(long orderId, CreditCard creditCard) throws OrderNotFoundException {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setPaymentMethod(creditCard);
            order.setOrderStatus(PROCESSED);

            return orderRepository.save(order);
        } else {
            throw new OrderNotFoundException("Order not found with orderId" + orderId);
        }
    }

    @Override
    public void setStatus(long orderId, OrderStatus status) throws OrderNotFoundException {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setOrderStatus(status);
            orderRepository.save(order);
        } else {
            throw new OrderNotFoundException("Order not found with orderId" + orderId);
        }
    }
}
