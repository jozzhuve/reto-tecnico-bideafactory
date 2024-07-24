package pe.bideafactory.api.booking.model;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BookRequest {

  private String id;
  private String name;
  private String lastname;
  private Integer age;
  private String phoneNumber;
  private Date startDate;
  private Date endDate;
  @NotNull(message = "required property")
  private String houseId;
  private String discountCode;

}
