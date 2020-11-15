package pl.sda.library.domain.model.exception;

import pl.sda.library.domain.model.Author;

public class AuthorAlreadyExistException extends ObjectAlreadyExistException {
    public AuthorAlreadyExistException(Author author) {
        super("Author already exists: " + author.toString());
    }
}
