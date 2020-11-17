package pl.sda.library.domain.model.exception;

public class UserBookDoesNotExistException extends ObjectDoesNotExistException {
    public UserBookDoesNotExistException(Long id) {
        super("UserBook with id = " + id + " does not exist");
    }
}
