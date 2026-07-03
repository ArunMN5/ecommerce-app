package ecommerce_app.dto.request;

import lombok.Data;

@Data
public class ProductRequest {

    private String productName;
    private String brandName;
    private Double price;
    private Integer stock;

}
