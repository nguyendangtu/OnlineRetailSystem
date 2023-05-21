package edu.miu.cs.cs544.shopping.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : JOHNNGUYEN
 * @since : 5/21/2023, Sun
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderStatus {
    @Id
    @GeneratedValue
    private Long id;

    private String status;
}
