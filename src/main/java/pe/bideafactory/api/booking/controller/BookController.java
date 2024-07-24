package pe.bideafactory.api.booking.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pe.bideafactory.api.booking.model.BookRequest;
import pe.bideafactory.api.booking.model.BookResponse;
import pe.bideafactory.api.booking.service.BookService;
import reactor.core.publisher.Mono;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class BookController {

  private final BookService bookService;

  @Operation(summary = "Reserva de casa")
  @ApiResponse(responseCode = "200",
      content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
  @PostMapping("/book")
  @ResponseStatus(HttpStatus.OK)
  public Mono<BookResponse> bookingHouse(@Valid @RequestBody BookRequest bookRequest) {
    return bookService.bookingHouse(bookRequest);
  }

}
