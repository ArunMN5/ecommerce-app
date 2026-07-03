package ecommerce_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private String message;
    private boolean status;
    private HttpStatus httpStatus;
    private Object data;

    //this type also works for both return type
//    public Response(String message, boolean status, HttpStatus httpStatus) {
//        this.message = message;
//        this.status = status;
//        this.httpStatus = httpStatus;
//    }
//
//    public Response(String message, boolean status, HttpStatus httpStatus, Object data) {
//        this.message = message;
//        this.status = status;
//        this.httpStatus = httpStatus;
//        this.data = data;
//    }

    @Override
    public String toString() {
        return  "Response{" + "message=" + message + ", status=" + status + ", httpStatus=" + httpStatus + '}';
    }


}
