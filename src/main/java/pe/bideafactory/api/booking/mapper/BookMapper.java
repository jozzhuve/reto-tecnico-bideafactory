package pe.bideafactory.api.booking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import pe.bideafactory.api.booking.entity.Book;
import pe.bideafactory.api.booking.model.BookRequest;

@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface BookMapper {

  Book toBooking(BookRequest request);

}
