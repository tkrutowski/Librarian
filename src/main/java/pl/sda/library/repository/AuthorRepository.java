package pl.sda.library.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.library.model.Author;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class AuthorRepository {

    private AuthorDtoRepository authorDtoRepository;
    private DtoFactory dtoFactory;

    public boolean add(Author author) {
        authorDtoRepository.save(dtoFactory.createAuthorDto(author));
        return true;
    }

    public List<Author> getAllAuthors() {
        List<Author> authorList = new ArrayList<>();
        authorDtoRepository.findAll().iterator().forEachRemaining(authorDto -> authorList.add(authorDto.toModel()));
        return authorList;
    }

    public void deleteAuthor(long id) {
        authorDtoRepository.deleteById(id);
    }
}
