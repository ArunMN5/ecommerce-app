package ecommerce_app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequest {

    private String name;
    private Long phone;
    private String email;
    private String password;


}
