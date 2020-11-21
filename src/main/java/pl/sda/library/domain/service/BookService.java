package pl.sda.library.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.model.exception.BookAlreadyExistException;
import pl.sda.library.domain.model.exception.BookDoesNotExistException;
import pl.sda.library.domain.port.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {

    private BookRepository bookRepository;

    public Book addBook(Book book) {
        int i = 0;
        if(isBookExist(book))
            throw new BookAlreadyExistException(book);

        return bookRepository.add(book).get();
    }

    private boolean isBookExist(Book book) {
        boolean isExist = false;
        List<Book> allByTitle = bookRepository.findAllByTitle(book.getTitle());
        if(allByTitle.size() > 0) {
            for (Book bookFound:allByTitle) {
                if(book.equals(bookFound)) {
                    isExist = true;
                    break;
                }
            }
        }
        return isExist;
    }

    public Book findBook(Long id) {
        Optional<Book> bookById = bookRepository.findById(id);
        if (!bookById.isPresent()) {
            throw new BookDoesNotExistException(id);
        }
        return bookById.get();
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book editBook(Book book, Long id) {
        return new Book();
    }

    public void deleteBook(Long id) {
    bookRepository.delete(id);
    }

    public List<Book> findAllBooks() {
        return new ArrayList<>();
    }
}

