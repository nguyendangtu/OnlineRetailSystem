package edu.miu.cs.cs544.shopping.service;

import edu.miu.cs.cs544.model.CreditCard;
import edu.miu.cs.cs544.shopping.domain.Order;
import edu.miu.cs.cs544.shopping.domain.OrderItem;
import edu.miu.cs.cs544.shopping.domain.OrderStatus;
import edu.miu.cs.cs544.shopping.service.impl.OrderNotFoundException;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders(Long customerId);

    Order getOrderDetail(Long orderId);

    Order createOrder(Order order);

    Order addItemToOrder(Long orderId, OrderItem orderItem) throws OrderNotFoundException;

    Order orderPayment(Long orderId, CreditCard creditCard) throws OrderNotFoundException;

    void setStatus(Long orderId, OrderStatus status) throws OrderNotFoundException;

}
