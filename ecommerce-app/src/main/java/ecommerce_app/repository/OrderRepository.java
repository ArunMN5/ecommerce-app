package ecommerce_app.repository;

import ecommerce_app.entity.Order;
import ecommerce_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

   // List<Order> findByUserEmail(String userEmail);

   // Optional<User> findByEmail(String email);

}
