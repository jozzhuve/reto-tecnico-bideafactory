package pe.bideafactory.api.booking.service.impl;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.bideafactory.api.booking.entity.Book;
import pe.bideafactory.api.booking.exception.BookException;
import pe.bideafactory.api.booking.mapper.BookMapper;
import pe.bideafactory.api.booking.model.BookRequest;
import pe.bideafactory.api.booking.model.BookResponse;
import pe.bideafactory.api.booking.repository.BookRepository;
import pe.bideafactory.api.booking.service.BookService;
import pe.bideafactory.api.booking.thrirdparty.internal.discount.DiscountApiClient;
import pe.bideafactory.api.booking.thrirdparty.internal.discount.dto.DiscountDtoRequest;
import pe.bideafactory.api.booking.thrirdparty.internal.discount.dto.DiscountDtoResponse;
import reactor.core.publisher.Mono;

import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@Service
@AllArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

  private final DiscountApiClient discountApiClient;
  private final BookRepository bookRepository;
  private final BookMapper bookMapper;

  @Override
  public Mono<BookResponse> bookingHouse(BookRequest bookRequest) {
    log.debug("request for book house {} ", new Gson().toJson(bookRequest));

    retrieveDiscount(bookRequest);

    Book book = bookMapper.toBooking(bookRequest);
    book.setUserCreation(book.getId());
    book.setCreationDate(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    bookRepository.save(book);

    return Mono.just(BookResponse.builder()
        .code(OK.value())
        .message("Book Accepted")
        .build());
  }

  private DiscountDtoRequest buildDiscountDtoRequest(BookRequest bookRequest) {
    return DiscountDtoRequest.
        builder()
        .userId(bookRequest.getId())
        .houseId(bookRequest.getHouseId())
        .discountCode(bookRequest.getDiscountCode())
        .build();
  }

  private DiscountDtoResponse retrieveDiscount(BookRequest bookRequest) {
    DiscountDtoResponse discountDtoResponse;
    try {

      discountDtoResponse
          = discountApiClient.retrieveDiscount(buildDiscountDtoRequest(bookRequest)).getBody();
      if (Objects.isNull(discountDtoResponse.getDiscountCode())) {
        throw new BookException(
            BAD_REQUEST.value(),
            "Conflict",
            "Invalid discount"
        );
      }
      log.debug("Discount response {}", new Gson().toJson(discountDtoResponse));

    } catch (BookException bex) {
      throw bex;
    }
    return discountDtoResponse;
  }


}
