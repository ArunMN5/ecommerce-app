package ecommerce_app.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private String brandName;
    private Double price;
    private Integer stock;
    private Date date;


}

