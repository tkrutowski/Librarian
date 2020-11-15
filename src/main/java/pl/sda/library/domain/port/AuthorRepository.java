package pl.sda.library.domain.port;

import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.Author;

import java.util.List;
import java.util.Optional;

@Component
public interface AuthorRepository {

    Long add(Author author);

    Optional<Author> findById(Long id);

    List<Author> findAll();

    void delete(Long id);

    Optional<Author> edit(Author author);

    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);
}
