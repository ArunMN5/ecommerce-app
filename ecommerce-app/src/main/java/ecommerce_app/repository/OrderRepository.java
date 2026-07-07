package ecommerce_app.repository;

import ecommerce_app.entity.Order;
import ecommerce_app.entity.Product;
import ecommerce_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


    List<Order> findByUser(User user);

    // Optional<User> findByEmail(String email);

    // List<Order> findByUserEmail(String userEmail);

   // List<Order> findByProductName(Product product);

}
