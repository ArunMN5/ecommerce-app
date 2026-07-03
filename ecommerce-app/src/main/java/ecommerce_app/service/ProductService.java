package ecommerce_app.service;

import ecommerce_app.dto.Response;
import ecommerce_app.dto.request.ProductRequest;

public interface ProductService {

    Response addProduct(ProductRequest  productRequest);

    Response getAllProducts();

    Response getProductById(Long id);

    Response getProductByName(String productName);

    Response updateProduct();

    Response deleteProduct(Long id);


}
