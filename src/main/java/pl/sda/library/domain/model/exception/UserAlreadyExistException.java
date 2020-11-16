package pl.sda.library.domain.model.exception;

import pl.sda.library.domain.model.User;

public class UserAlreadyExistException extends ObjectAlreadyExistException {
    public UserAlreadyExistException(User user) {
        super("User already exists: " + user.toString());
    }
}
