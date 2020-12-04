package pl.sda.library.domain.model.exception;

public class UserDoesNotExistException extends ObjectDoesNotExistException {
    public UserDoesNotExistException(String username) {
        super("User with with login: " + username + " does not exist");
    }

    public UserDoesNotExistException(Long id) {
        super("User with id = " + id + " does not exist");
    }
}
