package pl.sda.library.infrastructure.jpa;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.Author;
import pl.sda.library.domain.model.exception.AuthorAlreadyExistException;
import pl.sda.library.domain.model.exception.AuthorDoesNotExistException;
import pl.sda.library.domain.port.AuthorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class AuthorRepositoryAdapter implements AuthorRepository {

    private  AuthorDtoRepository authorDtoRepository;

    @Override
    public Long addAuthor(Author author) {
        if(isExistByFirstNameAndLastName(author.getFirstName(), author.getLastName())){
            throw new AuthorAlreadyExistException();
        }
        return authorDtoRepository.save(AuthorDto.fromDomain(author)).getId();
    }

    @Override
    public Optional<Author> getAuthorById(Long id) {
        Optional<AuthorDto> byId = authorDtoRepository.findById(id);
        if(byId.isPresent()){
            return Optional.of(byId.get().toModel());
        }
        return Optional.empty();
    }

    @Override
    public List<Author> getAllAuthors() {
        List<Author> authorList = new ArrayList<>();
        authorDtoRepository.findAll().iterator().forEachRemaining(authorDto -> authorList.add(authorDto.toModel()));
        return authorList;
    }

    @Override
    public void deleteAuthor(long id) {
        if(!isExistById(id)){
            throw new AuthorDoesNotExistException(id);
        }
        authorDtoRepository.deleteById(id);
    }

    @Override
    public Optional<Author> editAuthor(Author author) {
        return Optional.of(authorDtoRepository.save(AuthorDto.fromDomain(author)).toModel());
    }

    boolean isExistById(Long id) {
        return authorDtoRepository.findById(id).isPresent();
    }

    boolean isExistByFirstNameAndLastName(String firstName, String lastName) {
        return authorDtoRepository.findAuthorDtoByFirstNameAndLastName(firstName, lastName).isPresent();
    }
}
