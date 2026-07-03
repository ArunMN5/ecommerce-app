package ecommerce_app.serviceImpl;

import ecommerce_app.config.JwtUtil;
import ecommerce_app.dto.Response;
import ecommerce_app.dto.request.LoginRequest;
import ecommerce_app.dto.request.UserRequest;
import ecommerce_app.entity.User;
import ecommerce_app.repository.UserRepository;
import ecommerce_app.service.UserService;
import ecommerce_app.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public Response registerUser(UserRequest userRequest) {

        User user = new User();
        user.setName(userRequest.getName());
        user.setPhone(userRequest.getPhone());
        user.setEmail(userRequest.getEmail());
        //user.setPassword(userRequest.getPassword());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());

        userRepository.save(user);

        return new Response(Constants.USER_ADDED, true, HttpStatus.CREATED, null);
    }

    @Override
    public Response loginUser(LoginRequest loginRequest) {

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user == null) {
            return new Response("User not found", false, HttpStatus.NOT_FOUND, null);
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return new Response("Invalid Password", false, HttpStatus.UNAUTHORIZED, null);
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new Response("Login Successful", true, HttpStatus.OK, token);
    }


    @Override
    public Response getUserById(Long id) {
        return null;
    }
}
