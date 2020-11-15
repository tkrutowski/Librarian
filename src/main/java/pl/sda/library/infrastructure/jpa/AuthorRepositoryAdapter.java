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
    public Optional<Author> findById(Long id) {
        return authorDtoRepository.findById(id).map(authorDto -> authorDto.toDomain());
    }

    @Override
    public List<Author> findAll() {
        List<Author> authorList = new ArrayList<>();
        authorDtoRepository.findAll().iterator().forEachRemaining(authorDto -> authorList.add(authorDto.toDomain()));
        return authorList;
    }

    @Override
    public void delete(Long id) {

        authorDtoRepository.deleteById(id);
    }

    @Override
    public Optional<Author> edit(Author author) {
        return Optional.of(authorDtoRepository.save(AuthorDto.fromDomain(author)).toDomain());
    }

    @Override
    public Optional<Author> findByFirstNameAndLastName(String firstName, String lastName) {
        return authorDtoRepository.findAuthorDtoByFirstNameAndLastName(firstName, lastName)
                .map(authorDto -> authorDto.toDomain());
    }
}
