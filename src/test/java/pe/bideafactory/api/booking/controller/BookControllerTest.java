package pe.bideafactory.api.booking.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pe.bideafactory.api.booking.model.BookRequest;
import pe.bideafactory.api.booking.model.BookResponse;
import pe.bideafactory.api.booking.service.BookService;
import reactor.core.publisher.Mono;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BookControllerTest {

  @InjectMocks
  private BookController controller;
  @Mock
  private BookService bookService;


  @BeforeEach
  void setUo() {
    controller = new BookController(bookService);
  }

  @Test
  void bookingHouse_when_SuccessFullBooking() {
    MockHttpServletRequest request = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    when(bookService.bookingHouse(any()))
        .thenReturn(buildBookResponse());
    Mono<BookResponse> test = controller.bookingHouse(buildRequest());
    assertThat(test.block().getCode()).isEqualTo(200);
  }

  private Mono<BookResponse> buildBookResponse() {
    return Mono.just(BookResponse.builder()
        .code(OK.value())
        .message("Book Accepted")
        .build());
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

}
