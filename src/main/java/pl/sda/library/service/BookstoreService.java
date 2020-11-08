package pl.sda.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.library.model.Bookstore;
import pl.sda.library.repository.BookstoreRepository;
import pl.sda.library.service.exceptions.ObjectAlreadyExistException;
import pl.sda.library.service.exceptions.ObjectDoesNotExistException;

import java.util.List;

@Service
@AllArgsConstructor
public class BookstoreService {

    BookstoreRepository bookstoreRepository;

    public Long addBookstore(Bookstore bookstore) {
        if(alreadyExist(bookstore))
            throw new ObjectAlreadyExistException("Podana księgarnia już istnieje w bazie danych.");
        Long id = bookstoreRepository.addBookstore(bookstore);
        return id;
    }

    public List<Bookstore> getAllBookstores() {
        return bookstoreRepository.getAllBookstores();
    }

    public void delBookstore(long id) {
        bookstoreRepository.deleteBookstore(id);
    }

    public Bookstore getBookstore(Long id) {
        Bookstore bookstoreById = bookstoreRepository.getBookstoreById(id);
        if(bookstoreById.getIdBookstore() == null)
            throw new ObjectDoesNotExistException("Podana księgarnia nie istnieje w bazie danych.");
        else
            return bookstoreById;
    }

    public Bookstore editBookstore(Bookstore bookstoreToEdit) {
        Bookstore bookstoreById = bookstoreRepository.getBookstoreById(bookstoreToEdit.getIdBookstore());
        if(bookstoreById.getIdBookstore() == null)
            throw new ObjectDoesNotExistException("Podana księgarnia nie istnieje w bazie danych.");

        bookstoreById.setWww(bookstoreToEdit.getWww());
        bookstoreById.setName(bookstoreToEdit.getName());

        return bookstoreRepository.editBookstore(bookstoreById);
    }

    private boolean alreadyExist(Bookstore bookstore) {
        return bookstoreRepository.isExist(bookstore.getName());
    }
}
