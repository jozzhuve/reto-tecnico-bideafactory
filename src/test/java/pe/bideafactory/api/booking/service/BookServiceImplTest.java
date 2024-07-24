package pe.bideafactory.api.booking.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pe.bideafactory.api.booking.entity.Book;
import pe.bideafactory.api.booking.exception.BookException;
import pe.bideafactory.api.booking.mapper.BookMapper;
import pe.bideafactory.api.booking.model.BookRequest;
import pe.bideafactory.api.booking.model.BookResponse;
import pe.bideafactory.api.booking.repository.BookRepository;
import pe.bideafactory.api.booking.service.impl.BookServiceImpl;
import pe.bideafactory.api.booking.thrirdparty.internal.discount.DiscountApiClient;
import pe.bideafactory.api.booking.thrirdparty.internal.discount.dto.DiscountDtoResponse;
import reactor.core.publisher.Mono;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BookServiceImplTest {

  @InjectMocks
  private BookServiceImpl bookService;
  @Mock
  private DiscountApiClient discountApiClient;
  @Mock
  private BookRepository bookRepository;
  @Mock
  private BookMapper bookMapper;

  @BeforeEach
  void setUo() {
    bookService = new BookServiceImpl(discountApiClient, bookRepository, bookMapper);
  }

  @Test
  void bookingHouse_when_SuccessFullBooking() {
    MockHttpServletRequest request = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    when(discountApiClient.retrieveDiscount(any()))
        .thenReturn(buildDiscountDtoResponse("D0542A23"));
    when(bookRepository.save(any()))
        .thenReturn(new Book());
    when(bookMapper.toBooking(any())).thenReturn(new Book());
    Mono<BookResponse> test = bookService.bookingHouse(buildRequest());
    assertThat(test.block().getCode()).isEqualTo(200);
  }

  @Test
  void bookingHouse_when_InvalidDiscount() {
    MockHttpServletRequest request = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    when(discountApiClient.retrieveDiscount(any()))
        .thenReturn(buildDiscountDtoResponse(null));
    when(bookRepository.save(any()))
        .thenReturn(new Book());
    when(bookMapper.toBooking(any())).thenReturn(new Book());
    Assertions.assertThrows(BookException.class, () -> bookService.bookingHouse(buildRequest()));
  }

  private BookRequest buildRequest() {
    BookRequest br = new BookRequest();
    br.setId("14564088-4");
    br.setName("Gonzalo");
    br.setLastname("Perez");
    br.setAge(33);
    br.setPhoneNumber("56988123222");
    br.setStartDate(new Date());
    br.setEndDate(new Date());
    br.setHouseId("213132");
    br.setDiscountCode("D0542A23");
    return br;
  }

  private ResponseEntity<DiscountDtoResponse> buildDiscountDtoResponse(String discountCode) {
    DiscountDtoResponse discountDtoResponse = new DiscountDtoResponse();
    discountDtoResponse.setHouseId("123456");
    discountDtoResponse.setDiscountCode(discountCode);
    discountDtoResponse.setId("897");
    discountDtoResponse.setUserId("14564088-4");
    discountDtoResponse.setStatus(true);
    return ResponseEntity.ok(discountDtoResponse);
  }

}
