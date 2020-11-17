package pl.sda.library.domain.model.exception;

import pl.sda.library.domain.model.UserBook;

public class UserBookAlreadyExistException extends ObjectAlreadyExistException {
    public UserBookAlreadyExistException(UserBook userBook) {
        super("UserBook already exists: " + userBook.toString());
    }
}
