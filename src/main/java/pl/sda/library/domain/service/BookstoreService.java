package pl.sda.library.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.library.domain.model.Bookstore;
import pl.sda.library.domain.model.exception.BookstoreAlreadyExistException;
import pl.sda.library.domain.model.exception.BookstoreDoesNotExistException;
import pl.sda.library.domain.port.BookstoreRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookstoreService {

    BookstoreRepository bookstoreRepository;

    public Long addBookstore(Bookstore bookstore) {
        Optional<Bookstore> optionalBookstore = bookstoreRepository.findByName(bookstore.getName());
        if (optionalBookstore.isPresent()) {
            throw new BookstoreAlreadyExistException(bookstore);
        }
        return bookstoreRepository.add(bookstore);
    }

    public Bookstore editBookstore(Bookstore bookstoreToEdit) {
        Optional<Bookstore> bookstoreById = bookstoreRepository.findById(bookstoreToEdit.getIdBookstore());
        if (!bookstoreById.isPresent()) {
            throw new BookstoreDoesNotExistException(bookstoreToEdit.getIdBookstore());
        }
        Bookstore bookstoreTemp = bookstoreById.get();
        bookstoreTemp.setUrl(bookstoreToEdit.getUrl());
        bookstoreTemp.setName(bookstoreToEdit.getName());

        return bookstoreRepository.edit(bookstoreTemp).get();
    }

    public void deleteBookstore(Long id) {
        bookstoreRepository.delete(id);
    }

    public Bookstore findBookstore(Long id) {
        Optional<Bookstore> bookstoreById = bookstoreRepository.findById(id);
        if (!bookstoreById.isPresent()) {
            throw new BookstoreDoesNotExistException(id);
        }
        return bookstoreById.get();
    }

    public List<Bookstore> findAllBookstores() {
        return bookstoreRepository.findAll();
    }
}
