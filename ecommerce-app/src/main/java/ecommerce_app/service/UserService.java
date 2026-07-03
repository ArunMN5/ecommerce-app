package ecommerce_app.service;

import ecommerce_app.dto.Response;
import ecommerce_app.dto.request.LoginRequest;
import ecommerce_app.dto.request.UserRequest;

public interface UserService {

   Response registerUser(UserRequest  userRequest);

   Response loginUser(LoginRequest  loginRequest);

   Response getUserById(Long id);


}
