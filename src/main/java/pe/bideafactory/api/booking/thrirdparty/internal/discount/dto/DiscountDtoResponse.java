package pe.bideafactory.api.booking.thrirdparty.internal.discount.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountDtoResponse {

  private String houseId;
  private String discountCode;
  private String id;
  private String userId;
  private boolean status;

}
