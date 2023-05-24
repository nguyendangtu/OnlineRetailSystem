package edu.miu.cs.cs544.shopping.service.impl;

import edu.miu.cs.cs544.feignclient.CustomerFeignClient;
import edu.miu.cs.cs544.feignclient.ProductFeignClient;
import edu.miu.cs.cs544.model.CreditCard;
import edu.miu.cs.cs544.model.Customer;
import edu.miu.cs.cs544.shopping.domain.Order;
import edu.miu.cs.cs544.shopping.domain.OrderItem;
import edu.miu.cs.cs544.shopping.domain.OrderStatus;
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
    private ProductFeignClient productFeignClient;

    @Autowired
    private CustomerFeignClient customerFeignClient;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public Order getOrderDetail(Long orderId) {
        return this.orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public Order createOrder(Order order) {
        if (order.getCustomerId() == null) {
            return null;
        } else {
            order.setOrderStatus(NEW);
            order = orderRepository.save(order);

            Customer customer = customerFeignClient.getCustomer(order.getCustomerId()).getBody();
            order.setCustomer(customer);
            order.getOrderItems().forEach(item -> {
                item.setProduct(productFeignClient.getProduct(item.getProductId()).getBody());
            });
            return order;
        }
    }

    @Override
    public Order addItemToOrder(Long orderId, OrderItem orderItem) throws OrderNotFoundException {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (orderItem.getProductId() == null || (!order.getOrderStatus().name().equalsIgnoreCase(NEW.name()))) {
            return null;
        }
        Boolean flags[] = new Boolean[1];
        flags[0] = true;
        if (order != null) {
            order.getOrderItems().forEach(orderItem1 -> {
                if (orderItem1.getProductId() == orderItem.getProductId()) {
                    orderItem1.setQuantity(orderItem1.getQuantity() + orderItem.getQuantity());
                    flags[0] = false;
                }
            });
            if (flags[0]) {
                orderItem.setProduct(productFeignClient.getProduct(orderItem.getProductId()).getBody());
                order.getOrderItems().add(orderItem);
            }

            order = orderRepository.save(order);
            order.setCustomer(customerFeignClient.getCustomer(order.getCustomerId()).getBody());
            order.getOrderItems().forEach(i -> i.setProduct(productFeignClient.getProduct(i.getProductId()).getBody()));
            return order;
        } else {
            throw new OrderNotFoundException("Order not found with orderId" + orderId);
        }
    }

    @Override
    public Order orderPayment(Long orderId, CreditCard creditCard) throws OrderNotFoundException {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            Customer customer = customerFeignClient.getCustomer(order.getCustomerId()).getBody();
            List<CreditCard> cards = customer.getCreditCard();
            List<CreditCard> creditCards = cards.stream().filter(c -> c.getCreditCardNumber().equalsIgnoreCase(creditCard.getCreditCardNumber())).collect(Collectors.toList());
            CreditCard card = creditCards.stream().findFirst().orElse(null);
            order.setCreditCardId(card.getId());

            order.setOrderStatus(PROCESSED);

            order = orderRepository.save(order);
            order.setPaymentMethod(card);
            order.setCustomer(customer);
            order.getOrderItems().forEach(i -> i.setProduct(productFeignClient.getProduct(i.getProductId()).getBody()));
            return order;
        } else {
            throw new OrderNotFoundException("Order not found with orderId" + orderId);
        }
    }

    @Override
    public void setStatus(Long orderId, OrderStatus status) throws OrderNotFoundException {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setOrderStatus(status);
            orderRepository.save(order);
        } else {
            throw new OrderNotFoundException("Order not found with orderId" + orderId);
        }
    }
}
