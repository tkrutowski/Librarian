package pl.sda.library.domain.model.exception;

public class AuthorDoesNotExistException extends ObjectDoesNotExistException {
    public AuthorDoesNotExistException(long id) {
        super("Nie ma autora o takim ID: " + id);
    }
}
