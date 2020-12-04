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

    public Bookstore editBookstore(Bookstore bookstoreToEdit, Long id) {
        Optional<Bookstore> bookstoreById = bookstoreRepository.findById(id);
        if (!bookstoreById.isPresent()) {
            throw new BookstoreDoesNotExistException(id);
        }

        bookstoreById.get().setName(bookstoreToEdit.getName());
        bookstoreById.get().setUrl(bookstoreToEdit.getUrl());

        return bookstoreRepository.edit(bookstoreById.get()).get();
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

    public Bookstore findBookstoreByName(String name) {
        Optional<Bookstore> bookstoreByName = bookstoreRepository.findByName(name);
        if (!bookstoreByName.isPresent()) {
            throw new BookstoreDoesNotExistException(name);
        }
        return bookstoreByName.get();
    }
    public List<Bookstore> findAllBookstores() {
        return bookstoreRepository.findAll();
    }
}
