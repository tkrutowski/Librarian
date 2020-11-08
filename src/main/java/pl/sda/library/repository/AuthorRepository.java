package pl.sda.library.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.library.model.Author;
import pl.sda.library.service.exceptions.ObjectDoesNotExistException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class AuthorRepository {

    private AuthorDtoRepository authorDtoRepository;
    private DtoFactory dtoFactory;

    public Long add(Author author) {
        Long id = 0L;
        AuthorDto saved = authorDtoRepository.save(dtoFactory.createAuthorDto(author));
        id=saved.getId();
        return id;
    }

    public Author getAuthorById(Long id){
        Optional<AuthorDto> byId = authorDtoRepository.findById(id);
        if(byId.isPresent())
            return byId.get().toModel();
        else
            return new Author();

    }
    public List<Author> getAllAuthors() {
        List<Author> authorList = new ArrayList<>();
        authorDtoRepository.findAll().iterator().forEachRemaining(authorDto -> authorList.add(authorDto.toModel()));
        return authorList;
    }

    public void deleteAuthor(long id) {
        if(isExistById(id))
            authorDtoRepository.deleteById(id);
        else
            throw new ObjectDoesNotExistException("Nie ma autora o takim ID: " + id);
    }

    public Author editAuthor(Author author) {
        AuthorDto savedAuthor = authorDtoRepository.save(dtoFactory.createAuthorDto(author));
        return savedAuthor.toModel();
    }

    public boolean isExistById(Long id){
        Optional<AuthorDto> byId = authorDtoRepository.findById(id);
        if(byId.isPresent())
            return true;
        else
            return false;
    }
    public boolean isExist(String firstName, String lastName) {
        Optional<AuthorDto> authorDto = authorDtoRepository.findAuthorDtoByFirstNameAndLastName(firstName, lastName);
        if(authorDto.isPresent())
            return true;
        else
            return false;
    }
}
