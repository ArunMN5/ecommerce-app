package ecommerce_app.controller;

import ecommerce_app.dto.Response;
import ecommerce_app.dto.request.LoginRequest;
import ecommerce_app.dto.request.UserRequest;
import ecommerce_app.entity.User;
import ecommerce_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<Response> registerUser(@RequestBody UserRequest userRequest) {

    Response response = userService.registerUser(userRequest);

    return new ResponseEntity<>(response, response.getHttpStatus());
  }

  @PostMapping("/login")
  public ResponseEntity<Response> loginUser(@RequestBody LoginRequest loginRequest) {

    Response response = userService.loginUser(loginRequest);

    return new ResponseEntity<>(response, response.getHttpStatus());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Response> getUserById(@PathVariable Long id) {

    Response response = userService.getUserById(id);

    return new ResponseEntity<>(response, response.getHttpStatus());
  }

  @GetMapping("/test")
  public String test() {
    return "OK";
  }

}

//    POST   /users/register
//    POST   /users/login
//    GET    /users/{id}

