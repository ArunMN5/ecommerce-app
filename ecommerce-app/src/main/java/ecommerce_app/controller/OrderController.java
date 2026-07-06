package ecommerce_app.controller;

import ecommerce_app.dto.Response;
import ecommerce_app.dto.request.OrderRequest;
import ecommerce_app.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

//    @PostMapping("/place")
//    public ResponseEntity<Response> placeOrder(@RequestBody OrderRequest request) { //,Authentication authentication
//
//        //String email = authentication.getName();
//        Response response = orderService.placeOrder(request);
//        return new ResponseEntity<>(response, response.getHttpStatus());
//    }

    @PostMapping("/place")
    public ResponseEntity<Response> placeOrder(@RequestBody OrderRequest request, Authentication authentication) {
        return ResponseEntity.ok(orderService.placeOrder(request, authentication));
    }


    @GetMapping("/myOrders")
    public ResponseEntity<Response> getAllOrders() {
        Response response = orderService.getAllOrders();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}