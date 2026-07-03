package ecommerce_app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long phone;

    @Email(message = "Invalid email format")
    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    private Date createdAt;
    private Date updatedAt;


}
