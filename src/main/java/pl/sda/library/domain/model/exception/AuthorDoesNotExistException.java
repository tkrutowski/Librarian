package pl.sda.library.domain.model.exception;

public class AuthorDoesNotExistException extends ObjectDoesNotExistException {
    public AuthorDoesNotExistException(Long id) {
        super("Nie ma autora o takim ID: " + id);
    }
}
