package edu.miu.cs.cs544.shopping.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    private String orderNumber;

    @OneToOne
    @PrimaryKeyJoinColumn
    private OrderStatus orderStatus;

    @OneToMany
    private List<OrderLine> orderLines = new ArrayList();
}
