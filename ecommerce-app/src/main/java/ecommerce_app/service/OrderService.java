package ecommerce_app.service;

import ecommerce_app.dto.Response;
import ecommerce_app.dto.request.OrderRequest;
import ecommerce_app.dto.response.OrderResponse;
import ecommerce_app.entity.Order;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface OrderService {

  //  Response placeOrder(OrderRequest request);

    Response placeOrder(OrderRequest request, Authentication authentication);

    Response getAllOrders();

    Order getOrder(Long orderId);
}
