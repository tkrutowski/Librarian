package pl.sda.library.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.library.domain.model.exception.AuthorAlreadyExistException;
import pl.sda.library.domain.model.exception.AuthorDoesNotExistException;
import pl.sda.library.domain.model.exception.ObjectAlreadyExistException;
import pl.sda.library.domain.model.exception.ObjectDoesNotExistException;
import pl.sda.library.domain.model.Author;
import pl.sda.library.domain.port.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorService {

    private AuthorRepository authorRepository;

    public Long addAuthor(Author author)   {
        if(authorRepository.isExistByFirstNameAndLastName(author.getFirstName(), author.getLastName())){
            throw new AuthorAlreadyExistException();
        }
        Long id = authorRepository.addAuthor(author);
        return id;
    }

    public List<Author> getAllAuthors(){
        return authorRepository.getAllAuthors();
    }

    public void delAuthor(long id) {
        if(!authorRepository.isExistById(id)){
            throw new AuthorDoesNotExistException(id);
        }authorRepository.deleteAuthor(id);
    }

    public Author editAuthor(Author author)   {
        Optional<Author> authorById = authorRepository.getAuthorById(author.getId());
        if(!authorById.isPresent()) {
            throw new AuthorDoesNotExistException(author.getId());
        }

        Author authorTemp = authorById.get();
        authorTemp.setLastName(author.getLastName());
        authorTemp.setFirstName(author.getFirstName());

        return authorRepository.editAuthor(authorTemp).get();
    }

    public Author getAuthor(Long id) {
        Optional<Author> authorById = authorRepository.getAuthorById(id);
        if(!authorById.isPresent()){
            throw new AuthorDoesNotExistException(id);
        }
        return authorById.get();
    }
}
