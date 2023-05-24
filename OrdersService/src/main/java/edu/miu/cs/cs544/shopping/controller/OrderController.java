package edu.miu.cs.cs544.shopping.controller;

import edu.miu.cs.cs544.model.CreditCard;
import edu.miu.cs.cs544.shopping.domain.Order;
import edu.miu.cs.cs544.shopping.domain.OrderItem;
import edu.miu.cs.cs544.shopping.domain.OrderStatus;
import edu.miu.cs.cs544.shopping.service.OrderService;
import edu.miu.cs.cs544.shopping.service.impl.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @PutMapping("/{orderId}/items")
    public ResponseEntity<Order> addItemToOrder(@PathVariable long orderId, @RequestBody OrderItem orderItem) throws OrderNotFoundException {
        Order updatedOrder = orderService.addItemToOrder(orderId, orderItem);
        return ResponseEntity.ok(updatedOrder);
    }

    @PutMapping("/{orderId}/payment")
    public ResponseEntity<Order> orderPayment(@PathVariable long orderId, @RequestBody CreditCard creditCard) throws OrderNotFoundException {
        Order paidOrder = orderService.orderPayment(orderId, creditCard);
        return ResponseEntity.ok(paidOrder);
    }

    @PutMapping("/{orderId}/shipping")
    public ResponseEntity<Void> processShipping(@PathVariable long orderId) throws OrderNotFoundException {
        orderService.setStatus(orderId, OrderStatus.PROCESSED);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{orderId}/return")
    public ResponseEntity<Void> returned(@PathVariable long orderId) throws OrderNotFoundException {
        orderService.setStatus(orderId, OrderStatus.RETURNED);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getOrders(@PathVariable Long customerId) {
        List<Order> orders = orderService.getAllOrders(customerId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderDetails(@PathVariable Long orderId) {
        Order order = orderService.getOrderDetail(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }


}
