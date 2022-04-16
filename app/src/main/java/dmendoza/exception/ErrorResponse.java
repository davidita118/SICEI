package dmendoza.exception;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
   private Date timestamp;

   private Integer status;

   private String error;

   public ErrorResponse() {
      timestamp = new Date();
   }

   public ErrorResponse(HttpStatus httpStatus, String message) {
      this();

      this.status = httpStatus.value();
      this.error = message;
   }

   public Date getTimestamp() {
      return timestamp;
   }

   public void setTimestamp(Date timestamp) {
      this.timestamp = timestamp;
   }

   public Integer getStatus() {
      return status;
   }

   public void setStatus(Integer status) {
      this.status = status;
   }

   public String getError() {
      return error;
   }

   public void setError(String error) {
      this.error = error;
   }
}
