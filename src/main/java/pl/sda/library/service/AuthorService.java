package pl.sda.library.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.library.model.Author;
import pl.sda.library.repository.AuthorRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorService {

    @Autowired //TODO - usunąć
    private AuthorRepository authorRepository;


    boolean addAuthor(Author author){
        //TODO - sprawdzić przed dodaniem czy w bazie nie istnieje już zamienione imie nazwisko <-> nazwisko imie
        authorRepository.add(author);
        return true;
    }

    List<Author> getAllAuthors(){
        return authorRepository.getAllAuthors();
    }

    public void delAuthor(long id) {
        authorRepository.deleteAuthor(id);
    }
}
