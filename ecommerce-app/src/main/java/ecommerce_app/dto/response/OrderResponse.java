package ecommerce_app.dto.response;

import lombok.Data;

@Data
public class OrderResponse {

    private Long orderId;
    private String productName;
    private Integer quantity;
    private String orderDate;
    private Double orderTotal;
    private String orderStatus;


}
