package pl.sda.library.domain.model.exception;

import pl.sda.library.domain.model.Book;

public class BookAlreadyExistException extends ObjectAlreadyExistException {
    public BookAlreadyExistException(Book book) {
        super("Book already exists: " + book.toString());
    }
}
