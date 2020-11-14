package pl.sda.library.domain.port;

import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.Author;

import java.util.List;
import java.util.Optional;

@Component
public interface  AuthorRepository {

    Long addAuthor(Author author) ;

    Optional<Author> getAuthorById(Long id);

    List<Author> getAllAuthors();

    void deleteAuthor(long id);

    Optional<Author> editAuthor(Author author);

}
