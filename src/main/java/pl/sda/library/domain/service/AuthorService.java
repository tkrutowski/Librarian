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
        Optional<Author> optionalAuthor = authorRepository.findByFirstNameAndLastName(author.getFirstName(), author.getLastName());
        if (optionalAuthor.isPresent()) {
            throw new AuthorAlreadyExistException(author);
        }
        return authorRepository.add(author);
    }

    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public boolean deleteAuthor(Long id) {
        return authorRepository.delete(id);
    }

    public Author editAuthor(Author author) {
        Optional<Author> authorById = authorRepository.findById(author.getId());
        if (!authorById.isPresent()) {
            throw new AuthorDoesNotExistException(author.getId());
        }

        Author authorTemp = authorById.get();
        authorTemp.setLastName(author.getLastName());
        authorTemp.setFirstName(author.getFirstName());

        return authorRepository.edit(authorTemp).get();
    }

    public Author findAuthor(Long id) {
        Optional<Author> authorById = authorRepository.findById(id);
        if (!authorById.isPresent()) {
            throw new AuthorDoesNotExistException(id);
        }
        return authorById.get();
    }

    public Author findAuthor(String firstName, String lastName) {
        Optional<Author> authorById = authorRepository.findByFirstNameAndLastName(firstName, lastName);
        if (!authorById.isPresent()) {
            throw new AuthorDoesNotExistException(firstName + ' ' + lastName);
        }
        return authorById.get();
    }
}
