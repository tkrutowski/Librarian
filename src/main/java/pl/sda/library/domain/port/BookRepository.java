package pl.sda.library.domain.port;

import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.model.User;

import java.util.List;
import java.util.Optional;

@Component
interface BookRepository {

    Optional<Book> findById(Long id);

    boolean addBook(Book book);

    List<Book> findAllBooksByUser(User user);
}
