package pl.sda.library.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.library.domain.model.exception.AuthorAlreadyExistException;
import pl.sda.library.domain.model.exception.AuthorDoesNotExistException;
import pl.sda.library.domain.model.Author;
import pl.sda.library.domain.port.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorService {

    private AuthorRepository authorRepository;

    public Long addAuthor(Author author) {
        if (authorRepository.getByFirstNameAndLastName(author.getFirstName(), author.getLastName()).isPresent()) {
            throw new AuthorAlreadyExistException();
        }
        return authorRepository.add(author);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.getAll();
    }

    public void deleteAuthor(Long id) {
        if (!authorRepository.getById(id).isPresent()) {
            throw new AuthorDoesNotExistException(id);
        }
        authorRepository.delete(id);
    }

    public Author editAuthor(Author author) {
        Optional<Author> authorById = authorRepository.getById(author.getId());
        if (!authorById.isPresent()) {
            throw new AuthorDoesNotExistException(author.getId());
        }

        Author authorTemp = authorById.get();
        authorTemp.setLastName(author.getLastName());
        authorTemp.setFirstName(author.getFirstName());

        return authorRepository.edit(authorTemp).get();
    }

    public Author getAuthor(Long id) {
        Optional<Author> authorById = authorRepository.getById(id);
        if (!authorById.isPresent()) {
            throw new AuthorDoesNotExistException(id);
        }
        return authorById.get();
    }
}
