package edu.miu.cs.cs544.shopping.domain;


import edu.miu.cs.cs544.model.CreditCard;
import edu.miu.cs.cs544.model.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : JOHNNGUYEN
 * @since : 5/21/2023, Sun
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@EqualsAndHashCode(exclude = {"items", "subTotal", "creditCards"})
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private Double totalPrice;

    private Long customerId;

    private Long creditCardId;

    @Transient
    private Customer customer;

    @Transient
    private CreditCard paymentMethod;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems = new ArrayList<>();

}
