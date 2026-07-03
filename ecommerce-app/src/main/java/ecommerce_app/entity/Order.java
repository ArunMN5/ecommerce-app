package ecommerce_app.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private Integer quantity;
    private Double price;
    private String status;
    private String orderDate;
    private Double orderTotal;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
