package pe.bideafactory.api.booking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pe.bideafactory.api.booking.entity.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {

}
