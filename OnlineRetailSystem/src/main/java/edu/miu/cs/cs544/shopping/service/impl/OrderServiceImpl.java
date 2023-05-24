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
import java.util.stream.Collectors;

import static edu.miu.cs.cs544.shopping.domain.OrderStatus.NEW;
import static edu.miu.cs.cs544.shopping.domain.OrderStatus.PROCESSED;

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
            order.getOrderItems().forEach(item -> {
                if (item.getProduct() != null) {
                    item.setProduct(productRepository.findById(item.getProduct().getId()).orElse(null));
                }
            });
            order.setOrderStatus(NEW);

            return orderRepository.save(order);
        }
    }

    @Override
    public Order addItemToOrder(long orderId, OrderItem orderItem) throws OrderNotFoundException {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (orderItem.getProduct() == null || (!order.getOrderStatus().name().equalsIgnoreCase(NEW.name()))) {
            return null;
        }
        Boolean flags[] = new Boolean[1];
        flags[0] = true;
        if (order != null) {
            order.getOrderItems().forEach(orderItem1 -> {
                if (orderItem1.getProduct().getId() == orderItem.getProduct().getId()) {
                    orderItem1.setQuantity(orderItem1.getQuantity() + orderItem.getQuantity());
                    flags[0] = false;
                }
            });
            if (flags[0]) {
                orderItem.setProduct(productRepository.findById(orderItem.getProduct().getId()).orElse(null));
                order.getOrderItems().add(orderItem);
            }

            return orderRepository.save(order);
        } else {
            throw new OrderNotFoundException("Order not found with orderId" + orderId);
        }
    }

    @Override
    public Order orderPayment(long orderId, CreditCard creditCard) throws OrderNotFoundException {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            List<CreditCard> cards = order.getCustomer().getCreditCard();
            List<CreditCard> creditCards = cards.stream().filter(c -> c.getCreditCardNumber().equalsIgnoreCase(creditCard.getCreditCardNumber())).collect(Collectors.toList());
            order.setPaymentMethod(creditCards.stream().findFirst().orElse(null));
            order.setOrderStatus(PROCESSED);

            return orderRepository.save(order);
        } else {
            throw new OrderNotFoundException("Order not found with orderId" + orderId);
        }
    }

    @Override
    public void setStatus(long orderId, OrderStatus status) throws OrderNotFoundException {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setOrderStatus(status);
            orderRepository.save(order);
        } else {
            throw new OrderNotFoundException("Order not found with orderId" + orderId);
        }
    }
}
