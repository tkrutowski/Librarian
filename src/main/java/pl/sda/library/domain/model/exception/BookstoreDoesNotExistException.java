package pl.sda.library.domain.model.exception;

public class BookstoreDoesNotExistException extends ObjectDoesNotExistException {
    public BookstoreDoesNotExistException(Long id) {
        super("Bookstore with id = " + id + " does not exist");
    }
}
