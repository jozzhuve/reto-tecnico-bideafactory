package pe.bideafactory.api.booking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "booking", schema = "book")
public class Book {

  @Id
  private String id;
  private String name;
  private String lastname;
  private Integer age;
  private String phoneNumber;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private String houseId;
  private String discountCode;
  private String userCreation;
  private LocalDateTime creationDate;
  private String userUpdate;
  private LocalDateTime updateDate;

}
