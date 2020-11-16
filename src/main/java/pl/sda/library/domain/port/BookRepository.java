package pl.sda.library.domain.port;

import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.Book;
import java.util.List;
import java.util.Optional;

@Component
public interface BookRepository {

    Optional<Book> add(Book book);

    Optional<Book> edit(Book book);

    void delete(Long id);

    List<Book> findAll();

    Optional<Book> findById(Long id);

    List<Book> findAllByTitle(String title);

    Optional<Book> findByTitle(String title);
}
