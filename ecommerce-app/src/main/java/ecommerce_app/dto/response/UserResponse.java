package ecommerce_app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {

    private String id;
    private String name;
    private Long phone;
    private String email;
    private String createdAt;
    private String updatedAt;

}
