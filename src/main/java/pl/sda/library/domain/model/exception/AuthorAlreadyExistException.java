package pl.sda.library.domain.model.exception;

public class AuthorAlreadyExistException extends ObjectAlreadyExistException {
    public AuthorAlreadyExistException() {
        super("Taki autor już istnieje w bazie danych");
    }
}
