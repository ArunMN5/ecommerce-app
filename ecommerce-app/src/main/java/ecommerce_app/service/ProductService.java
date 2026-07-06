package ecommerce_app.service;

import ecommerce_app.dto.Response;
import ecommerce_app.dto.request.ProductRequest;
import ecommerce_app.entity.Product;

public interface ProductService {

    Response addProduct(ProductRequest  productRequest);

    Response getAllProducts();

    Response getProductById(Long id);

    Response getProductByName(String productName);

    Response findByProductNameContainingIgnoreCase(String productName);

    Response updateProduct();

    Response deleteProduct(Long id);


}
