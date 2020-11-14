package pl.sda.library.domain.port;

import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.model.User;

import java.util.List;

@Component
interface BookRepository {

    Book findById(Long id);

    boolean addBook(Book book);

    List<Book> findAllBooksByUser(User user);
}
