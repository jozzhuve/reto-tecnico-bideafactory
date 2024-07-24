package pe.bideafactory.api.booking.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookException extends RuntimeException {

  private Integer statusCode;
  private String error;
  private String message;

}
