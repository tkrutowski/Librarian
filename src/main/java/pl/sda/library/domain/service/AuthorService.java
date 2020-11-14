package pl.sda.library.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.library.domain.model.exception.ObjectAlreadyExistException;
import pl.sda.library.domain.model.exception.ObjectDoesNotExistException;
import pl.sda.library.domain.model.Author;
import pl.sda.library.domain.port.AuthorRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorService {

    private AuthorRepository authorRepository;

    public Long addAuthor(Author author)   {
        if(alreadyExist(author))
           throw new ObjectAlreadyExistException("Podany autor już istnieje w bazie danych.");
        Long id = authorRepository.add(author);
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
        Author authorById = authorRepository.getAuthorById(author.getId());
        if(authorById.getId() == null)
            throw new ObjectDoesNotExistException("Podany autor nie istnieje w bazie danych.");

        authorById.setLastName(author.getLastName());
        authorById.setFirstName(author.getFirstName());

        return authorRepository.editAuthor(authorById);
    }

    public Author getAuthor(Long id) {
        Author authorById = authorRepository.getAuthorById(id);
        if(authorById.getId() == null)
            throw new ObjectDoesNotExistException("Podany autor nie istnieje w bazie danych.");
        else
            return authorById;
    }
}
