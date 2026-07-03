package ecommerce_app.exceptions;
import ecommerce_app.dto.Response;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Resource Not Found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response> handleNotFound(ResourceNotFoundException ex) {

        Response response = new Response(ex.getMessage(), false, HttpStatus.NOT_FOUND, null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // 4. Out of Stock
    @ExceptionHandler(OutOfStockException.class)
    public ResponseEntity<Response> handleStock(OutOfStockException ex) {

        Response response = new Response(ex.getMessage(), false, HttpStatus.BAD_REQUEST, null);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // 5. Generic Exception (VERY IMPORTANT - fallback)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleGeneral(Exception ex) {

        Response response = new Response("Something went wrong: " + ex.getMessage(), false, HttpStatus.INTERNAL_SERVER_ERROR, null);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


//    // 2. Duplicate Resource
//    @ExceptionHandler(DuplicateResourceException.class)
//    public ResponseEntity<Response> handleDuplicate(DuplicateResourceException ex) {
//
//        Response response = new Response(
//                ex.getMessage(),
//                false,
//                HttpStatus.CONFLICT,
//                null
//        );
//
//        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
//    }
//
//    // 3. Invalid Request
//    @ExceptionHandler(InvalidRequestException.class)
//    public ResponseEntity<Response> handleInvalid(InvalidRequestException ex) {
//
//        Response response = new Response(
//                ex.getMessage(),
//                false,
//                HttpStatus.BAD_REQUEST,
//                null
//        );
//
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }


}