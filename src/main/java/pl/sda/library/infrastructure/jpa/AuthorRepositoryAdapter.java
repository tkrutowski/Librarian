package pl.sda.library.infrastructure.jpa;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.Author;
import pl.sda.library.domain.port.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class AuthorRepositoryAdapter implements AuthorRepository {

    private  AuthorDtoRepository authorDtoRepository;

    @Override
    public Long addAuthor(Author author) {
        return null;
    }

    @Override
    public Optional<Author> getAuthorById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Author> getAllAuthors() {
        return null;
    }

    @Override
    public void deleteAuthor(long id) {

    }

    @Override
    public Author editAuthor(Author author) {
        return null;
    }

    @Override
    public boolean isExistById(Long id) {
        return false;
    }

    @Override
    public boolean isExist(String firstName, String lastName) {
        return false;
    }
}
