package pl.sda.library.domain.model.exception;

import pl.sda.library.domain.model.Bookstore;

public class BookstoreAlreadyExistException extends ObjectAlreadyExistException {
    public BookstoreAlreadyExistException(Bookstore bookstore) {
        super("Bookstore already exists: " + bookstore.toString());

    }
}
