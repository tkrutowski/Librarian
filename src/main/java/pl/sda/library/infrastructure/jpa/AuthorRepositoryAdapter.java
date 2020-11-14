package pl.sda.library.infrastructure.jpa;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.Author;
import pl.sda.library.domain.port.AuthorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class AuthorRepositoryAdapter implements AuthorRepository {

    private AuthorDtoRepository authorDtoRepository;

    @Override
    public Long add(Author author) {
        return authorDtoRepository.save(AuthorDto.fromDomain(author)).getId();
    }

    @Override
    public Optional<Author> getById(Long id) {
        return authorDtoRepository.findById(id).map(authorDto -> authorDto.toModel());
    }

    @Override
    public List<Author> getAll() {
        List<Author> authorList = new ArrayList<>();
        authorDtoRepository.findAll().iterator().forEachRemaining(authorDto -> authorList.add(authorDto.toModel()));
        return authorList;
    }

    @Override
    public void delete(Long id) {
        authorDtoRepository.deleteById(id);
    }

    @Override
    public Optional<Author> edit(Author author) {
        return Optional.of(authorDtoRepository.save(AuthorDto.fromDomain(author)).toModel());
    }

    @Override
    public Optional<Author> getByFirstNameAndLastName(String firstName, String lastName) {
        return authorDtoRepository.findAuthorDtoByFirstNameAndLastName(firstName, lastName).map(authorDto -> authorDto.toModel());
    }
}
