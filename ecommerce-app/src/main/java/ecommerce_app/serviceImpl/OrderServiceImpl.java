//package ecommerce_app.serviceImpl;
//
//import ecommerce_app.dto.Response;
//import ecommerce_app.dto.request.OrderRequest;
//import ecommerce_app.dto.response.OrderResponse;
//import ecommerce_app.entity.Order;
//import ecommerce_app.entity.Product;
//import ecommerce_app.repository.OrderRepository;
//import ecommerce_app.repository.ProductRepository;
//import ecommerce_app.service.OrderService;
//import ecommerce_app.util.Constants;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class OrderServiceImpl implements OrderService {
//
//    private final OrderRepository orderRepository;
//    private final ProductRepository productRepository;
//
//    @Override
//    public Response placeOrder(OrderRequest request) {
//
//        // get product from DB
//        List<Product> products = productRepository.findByProductName(request.getProductName());
//
//        if (products.isEmpty()) {
//            return new Response(Constants.PRODUCT_NOT_FOUND, false, HttpStatus.NOT_FOUND,null);
//        }
//
//        Product product = products.get(0); // (no validation as per your style)
//
//        Order order = new Order();
//        order.setProductName(product.getProductName());
//        order.setQuantity(request.getQuantity());
//
//        // PRICE COMES FROM PRODUCT TABLE
//        order.setPrice(product.getPrice() * request.getQuantity());
//
//        order.setStatus("PLACED");
//
//        orderRepository.save(order);
//
//        OrderResponse response = new OrderResponse();
//        response.setOrderId(order.getId());
//        response.setProductName(order.getProductName());
//        response.setQuantity(order.getQuantity());
//        response.setOrderTotal(order.getPrice());
//        response.setOrderStatus(order.getStatus());
//
//        return new Response(Constants.SUCCESS, true, HttpStatus.CREATED, response);
//    }
//
//    @Override
//    public Response getAllOrders() {
//
//        List<Order> orders = orderRepository.findAll();
//
//        List<OrderResponse> list = new ArrayList<>();
//
//        for (Order o : orders) {
//
//            OrderResponse r = new OrderResponse();
//            r.setOrderId(o.getId());
//            r.setProductName(o.getProductName());
//            r.setQuantity(o.getQuantity());
//            r.setOrderTotal(o.getPrice());
//            r.setOrderStatus(o.getStatus());
//
//            list.add(r);
//        }
//
//        return new Response(Constants.SUCCESS, true, HttpStatus.OK, list);
//    }
//}

package ecommerce_app.serviceImpl;

import ecommerce_app.dto.Response;
import ecommerce_app.dto.request.OrderRequest;
import ecommerce_app.dto.response.OrderResponse;
import ecommerce_app.entity.Order;
import ecommerce_app.entity.Product;
import ecommerce_app.entity.User;
import ecommerce_app.repository.OrderRepository;
import ecommerce_app.repository.ProductRepository;
import ecommerce_app.repository.UserRepository;
import ecommerce_app.service.OrderService;
import ecommerce_app.util.Constants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public Response placeOrder(OrderRequest request, Authentication authentication) {

        // Get logged-in user email from JWT
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findByProductName(request.getProductName())
                .stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Stock check
        if (product.getStock() < request.getQuantity()) {
            return new Response("OUT OF STOCK", false, HttpStatus.BAD_REQUEST, null);
        }

        // Reduce stock
        product.setStock(product.getStock() - request.getQuantity());
        productRepository.save(product);

        // Create order
        Order order = new Order();
        order.setUser(user);              // this will map user with id ,to know who ordered
        order.setProductName(product.getProductName());
        order.setQuantity(request.getQuantity());

        order.setPrice(product.getPrice());
        order.setOrderTotal(product.getPrice() * request.getQuantity());
        order.setStatus("PLACED");
        order.setUser(user);       //imp GET ORDERS BASED ON PERTICULAR USER ONLY
        order.setOrderDate(LocalDate.now().toString());

        orderRepository.save(order);

        //THIS WILL RETURN RESPONSE TO USER CONSOLE AFTER ORDER PLACED
        OrderResponse response = new OrderResponse();

        response.setOrderId(order.getId());
        response.setProductName(order.getProductName());
        response.setQuantity(order.getQuantity());
        response.setOrderDate(order.getOrderDate());
        response.setOrderTotal(order.getOrderTotal());
        response.setOrderStatus(order.getStatus());

        return new Response("ORDER PLACED", true, HttpStatus.CREATED, response);
    }


    @Override
    public Response getAllOrders() {

        //THIS WILL EXTRACT USER EMAIL FOR JWT TOKEN AND VERIFY HERE TO GET ORDERS OF PERTICULAR USER
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Order> orders = orderRepository.findByUser(user);//THIS WILL RETURN ALL ORDERS BASED ON SAME USER ID ONLY
        List<OrderResponse> list = new ArrayList<>();

        for (Order o : orders) {

            OrderResponse r = new OrderResponse();

            r.setOrderId(o.getId());
            r.setProductName(o.getProductName());
            r.setQuantity(o.getQuantity());
            r.setOrderDate(o.getOrderDate());
            r.setOrderTotal(o.getOrderTotal());
            r.setOrderStatus(o.getStatus());

            list.add(r);
        }
        return new Response(Constants.SUCCESS, true, HttpStatus.OK, list);

    }

    @Override
    public Order getOrder(Long orderId) { //GETMAPPING ALSO PASS DATA USING PATHVARIABLE OR QUERY TO GET DATA
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
    }


 //   @Override
//    public Response getAllOrders() {
//
//        List<Order> orders = orderRepository.findByUser(user);
//       // List<Order> orders = orderRepository.findAll();
//        List<OrderResponse> list = new ArrayList<>();
//
//        for (Order o : orders) {
//
//            OrderResponse r = new OrderResponse();
//            r.setOrderId(o.getId());
//            r.setProductName(o.getProductName());
//            r.setQuantity(o.getQuantity());
//            r.setOrderDate(o.getOrderDate());
//            r.setOrderTotal(o.getOrderTotal());
//            r.setOrderStatus(o.getStatus());
//
//            list.add(r);
//        }
//
//        return new Response(Constants.SUCCESS, true, HttpStatus.OK, list);
//
//    }


}

//“How do you implement My Orders?”
//Answer should be:👉 “I fetch user from JWT SecurityContext and query orders by userId in repository.”


//    @Transactional
//    @Override
//    public Response placeOrder(OrderRequest request) {
//
//        // get product from DB
//        List<Product> products = productRepository.findByProductName(request.getProductName());
//
//        if (products.isEmpty()) {
//            return new Response(Constants.PRODUCT_NOT_FOUND, false, HttpStatus.NOT_FOUND, null);
//        }
//
//        Product product = products.get(0);
//
//        // Check stock
//        if (product.getStock() < request.getQuantity()) {
//            return new Response("OUT OF STOCK", false, HttpStatus.BAD_REQUEST, null);
//        }
//
//        // reduce stoke
//        product.setStock(product.getStock() - request.getQuantity());
//        productRepository.save(product);
//
//        Order order = new Order();
//
//        order.setProductName(product.getProductName());
//        order.setQuantity(request.getQuantity());
//        order.setPrice(product.getPrice());
//        order.setOrderTotal(product.getPrice() * request.getQuantity());
//        order.setStatus("PLACED");
//        order.setOrderDate(LocalDate.now().toString());
//
//        orderRepository.save(order);
//
//        OrderResponse response = new OrderResponse();
//        response.setOrderId(order.getId());
//        response.setProductName(order.getProductName());
//        response.setQuantity(order.getQuantity());
//        response.setOrderTotal(order.getOrderTotal());
//        response.setOrderStatus(order.getStatus());
//
//        return new Response(Constants.SUCCESS, true, HttpStatus.CREATED, response);
//    }
