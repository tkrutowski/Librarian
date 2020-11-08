package pl.sda.library.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.library.exceptions.ObjectDoesNotExistException;
import pl.sda.library.model.Author;
import pl.sda.library.repository.AuthorRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorService {

    @Autowired //TODO - usunąć
    private AuthorRepository authorRepository;


    public Long addAuthor(Author author) {
        if(alreadyExist(author))
        //TODO - rzucić jakimś wyjątkiem?
          // throw new Exception("Podany autor już istnieje w bazie danych.");
             return 0L;
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

    public Author editAuthor(Author author) throws ObjectDoesNotExistException {
        Author authorById = authorRepository.getAuthorById(author.getId());
        if(authorById.getId() == null)
            throw new ObjectDoesNotExistException("Podany autor nie istnieje w bazie danych.");

        authorById.setLastName(author.getLastName());
        authorById.setFirstName(author.getFirstName());

        return authorRepository.editAuthor(author);
    }

    public Author getAuthor(Long id) {
        return authorRepository.getAuthorById(id);
    }
}
