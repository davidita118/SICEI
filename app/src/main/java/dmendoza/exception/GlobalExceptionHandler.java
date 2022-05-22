package dmendoza.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dmendoza.exception.model.InternalServerErrorException;
import dmendoza.exception.model.NotFoundException;
import dmendoza.exception.model.UnprocessableEntityException;

@ControllerAdvice
public class GlobalExceptionHandler {
   
   @ExceptionHandler(UnprocessableEntityException.class)
   public ResponseEntity<ErrorResponse> uprocessableEntityHandler(UnprocessableEntityException exception) {
      return ResponseEntity.unprocessableEntity().body(new ErrorResponse(
         HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage()
      ));
   }

   @ExceptionHandler(NotFoundException.class)
   public ResponseEntity<ErrorResponse> notFoundHandler(NotFoundException exception) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(
         HttpStatus.NOT_FOUND, exception.getMessage()
      ));
   }

   @ExceptionHandler(InternalServerErrorException.class)
   public ResponseEntity<ErrorResponse> internalServerErrorHandler(InternalServerErrorException exception) {
      return ResponseEntity.internalServerError().body(new ErrorResponse(
         HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage()
      ));
   }
}
