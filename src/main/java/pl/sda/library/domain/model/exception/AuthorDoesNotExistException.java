package pl.sda.library.domain.model.exception;

public class AuthorDoesNotExistException extends ObjectDoesNotExistException {
    public AuthorDoesNotExistException(Long id) {
        super("Author with id = " + id + " does not exist");
    }
}
