package pl.sda.library.domain.model.exception;

public class UserDoesNotExistException extends ObjectDoesNotExistException {
    public UserDoesNotExistException(Long id) {
        super("User with id = " + id + " does not exist");
    }
}
