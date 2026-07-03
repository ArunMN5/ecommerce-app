package ecommerce_app.repository;

import ecommerce_app.entity.Product;
import ecommerce_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {


    Optional<Product> findById(Long id);

    List<Product> findByProductName(String name);

    List<Product> deleteById(Long id);

   // Optional<Product> findByProductName(String productName);

}
