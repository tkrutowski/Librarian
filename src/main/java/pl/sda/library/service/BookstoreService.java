package pl.sda.library.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.library.model.Bookstore;
import pl.sda.library.repository.BookstoreRepository;
import pl.sda.library.repository.DtoFactory;

import java.util.List;

@Service
@AllArgsConstructor
public class BookstoreService {

    @Autowired //TODO - usunąć
    BookstoreRepository bookstoreRepository;
    @Autowired //TODO - usunąć
    private DtoFactory dtoFactory;

    public boolean addBookstore(Bookstore bookstore) {
        //TODO - sprawdzić przed dodaniem czy nie istnieje już w bazie
       bookstoreRepository.addBookstore(bookstore);
        return true;
    }

    public List<Bookstore> getAllBookstores() {
        return bookstoreRepository.getAllBookstores();
    }

    public void delBookstore(long id) {
        bookstoreRepository.deleteBookstore(id);
    }

}
