package miu.edu.apigateway.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author : JOHNNGUYEN
 * @since : 5/22/2023, Mon
 **/
@Data
@Entity
@Table(name = "Role")
public class Role {
    
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String path;

    public Role(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public Role() {

    }
}
