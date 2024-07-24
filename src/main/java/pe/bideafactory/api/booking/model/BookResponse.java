package pe.bideafactory.api.booking.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Schema(
    description = "Retorna respuesta exitosa en caso que la reservacion sea correcta")
@Getter
@Setter
@Builder
public class BookResponse {

  private Integer code;
  private String message;

}
