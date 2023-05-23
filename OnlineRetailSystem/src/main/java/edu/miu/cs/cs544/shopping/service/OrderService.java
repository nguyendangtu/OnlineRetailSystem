package edu.miu.cs.cs544.shopping.service;

import edu.miu.cs.cs544.customer.domain.CreditCard;
import edu.miu.cs.cs544.shopping.domain.Order;
import edu.miu.cs.cs544.shopping.domain.OrderItem;
import edu.miu.cs.cs544.shopping.domain.OrderStatus;
import edu.miu.cs.cs544.shopping.service.impl.OrderNotFoundException;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders(Long customerId);

    Order getOrderDetail(long orderId);

    Order createOrder(Order order);

    Order addItemToOrder(long orderId, OrderItem orderItem) throws OrderNotFoundException;

    Order orderPayment(long orderId, CreditCard creditCard) throws OrderNotFoundException;

    void setStatus(long orderId, OrderStatus status) throws OrderNotFoundException;

}
