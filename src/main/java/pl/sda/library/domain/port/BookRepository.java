package pl.sda.library.domain.port;

import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.model.User;

import java.util.List;
import java.util.Optional;

@Component
public interface BookRepository {

    boolean add(Book book);

    Optional<Book> edit(Book book);

    void delete(Long id);

    List<Book> findAllByUser(User user);

    Optional<Book> findById(Long id);
}
