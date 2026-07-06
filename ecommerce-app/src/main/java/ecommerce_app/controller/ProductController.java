package ecommerce_app.controller;

import ecommerce_app.dto.Response;
import ecommerce_app.dto.request.ProductRequest;
import ecommerce_app.dto.response.ProductResponse;
import ecommerce_app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;

//    @PostMapping("/addProduct")
//    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest) {
//        ProductResponse productResponse =productService.addProduct(productRequest);
//        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
//    }

    @PostMapping("/addProducts")
    public ResponseEntity<Response> addProduct(@RequestBody ProductRequest request) {
        Response response = productService.addProduct(request);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("/getProductByName/{productName}")
    public ResponseEntity<Response> getProductByName(@PathVariable String productName) {
        Response response = productService.getProductByName(productName);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("/getProductByNames")
    public ResponseEntity<Response> findByProductNameContainingIgnoreCase(@RequestBody String productNames) {
        Response response = productService.findByProductNameContainingIgnoreCase(productNames);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }


    @GetMapping("/getAllProducts")
    public ResponseEntity<Response> getAllProducts() {
        Response response = productService.getAllProducts();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }


    @DeleteMapping
    public ResponseEntity<Response> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        Response response = productService.deleteProduct(id);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }


}
