package ecommerce_app.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class ProductResponse {

    private Long id;
    private String productName;
    private String brandName;
    private Double price;
    private Integer stock;
    private Date date;


}
