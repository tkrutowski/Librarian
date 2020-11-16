package pl.sda.library.domain.model.exception;

public class BookDoesNotExistException extends ObjectDoesNotExistException {
    public BookDoesNotExistException(Long id) {
        super("Book with id = " + id + " does not exist");
    }
}
