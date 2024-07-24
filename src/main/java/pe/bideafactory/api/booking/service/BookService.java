package pe.bideafactory.api.booking.service;

import pe.bideafactory.api.booking.model.BookRequest;
import pe.bideafactory.api.booking.model.BookResponse;
import reactor.core.publisher.Mono;

public interface BookService {

  Mono<BookResponse> bookingHouse(BookRequest bookRequest);

}
