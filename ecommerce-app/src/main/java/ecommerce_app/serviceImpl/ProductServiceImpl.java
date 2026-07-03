package ecommerce_app.serviceImpl;

import ecommerce_app.dto.Response;
import ecommerce_app.dto.request.ProductRequest;
import ecommerce_app.dto.response.ProductResponse;
import ecommerce_app.entity.Product;
import ecommerce_app.repository.ProductRepository;
import ecommerce_app.service.ProductService;
import ecommerce_app.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    // private final Constants constants;
     //  private final Response response;


    @Override
    public Response addProduct(ProductRequest productRequest) {

        if (productRequest.getProductName() == null || productRequest.getProductName().trim().isEmpty()) {
            return new Response(Constants.PRODUCT_DETAILS_NOT_EMPTY, false, HttpStatus.BAD_REQUEST, null);
        }

        //database
        Product product = new Product();

        product.setProductName(productRequest.getProductName());
        product.setBrandName(productRequest.getBrandName());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setDate(new Date());

        productRepository.save(product);

        //entity -> response
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setProductName(product.getProductName());
        productResponse.setBrandName(product.getBrandName());
        productResponse.setPrice(product.getPrice());
        productResponse.setStock(product.getStock());
        productResponse.setDate(product.getDate());


        return new Response(Constants.SUCCESS, true, HttpStatus.CREATED, productResponse);
    }

    @Override
    public Response getAllProducts() {

        List<Product> products = productRepository.findAll();

//        ProductResponse productResponse = new ProductResponse();
//
//        productResponse.setId(product.get().getId());
//        productResponse.setProductName(product.get().getProductName());
//        productResponse.setBrandName(product.get().getBrandName());
//        productResponse.setPrice(product.get().getPrice());
//        productResponse.setStock(product.get().getStock());
//        productResponse.setDate(product.get().getDate());

        Response response = new Response();
        response.setMessage(Constants.SUCCESS);
        response.setStatus(true);
        response.setHttpStatus(HttpStatus.OK);
        response.setData(products);

        return response;
    }

//    @Override
//    public Response getProductById(Long id) {
//
//        List<Product> products = productRepository.findById(id);
//        return new Response(Constants.SUCCESS, true, HttpStatus.OK, products);
//    }

    @Override
    public Response getProductById(Long id) {

        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {
            return new Response(Constants.PRODUCT_NOT_FOUND, false, HttpStatus.NOT_FOUND, null);
        }

        //it used send to client response & this is manual way without mapper hardcode
        ProductResponse productResponse = new ProductResponse();

        productResponse.setId(product.get().getId());
        productResponse.setProductName(product.get().getProductName());
        productResponse.setBrandName(product.get().getBrandName());
        productResponse.setPrice(product.get().getPrice());
        productResponse.setStock(product.get().getStock());
        productResponse.setDate(product.get().getDate());


        return new Response(Constants.SUCCESS, true, HttpStatus.OK, productResponse);
    }

    @Override
    public Response getProductByName(String productName) {

        List<Product> products = productRepository.findByProductName(productName);
        return new Response(Constants.SUCCESS, true, HttpStatus.OK, products);
    }

    @Override
    public Response updateProduct() {
        return null;
    }

    @Override
    public Response deleteProduct(Long id) {

        productRepository.deleteById(id);
        return new Response(Constants.SUCCESS, true, HttpStatus.OK, null);
    }


}
