package pe.bideafactory.api.booking.thrirdparty.internal.discount.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DiscountDtoRequest {

  private String userId;
  private String houseId;
  private String discountCode;

}
