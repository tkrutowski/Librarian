package pl.sda.library.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
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
        if(alreadyExist(author))
           throw new ObjectAlreadyExistException("Podany autor już istnieje w bazie danych.");
        Long id = authorRepository.addAuthor(author);
        return id;
    }

    private boolean alreadyExist(Author author) {
        return authorRepository.isExist(author.getFirstName(),author.getLastName());
    }

    public List<Author> getAllAuthors(){
        return authorRepository.getAllAuthors();
    }

    public void delAuthor(long id) {
        authorRepository.deleteAuthor(id);
    }

    public Author editAuthor(Author author)   {
        Optional<Author> authorById = authorRepository.getAuthorById(author.getId());
        if(!authorById.isPresent()) {
            throw new ObjectDoesNotExistException("Podany autor nie istnieje w bazie danych.");
        }

        Author authorTemp = authorById.get();

        authorTemp.setLastName(author.getLastName());
        authorTemp.setFirstName(author.getFirstName());

        return authorRepository.editAuthor(authorTemp);
    }

    public Author getAuthor(Long id) {
        Optional<Author> authorById = authorRepository.getAuthorById(id);
        if(!authorById.isPresent()){
            throw new ObjectDoesNotExistException("Podany autor nie istnieje w bazie danych.");
        }
        return authorById.get();
    }
}
