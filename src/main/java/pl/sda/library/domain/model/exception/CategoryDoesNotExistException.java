package pl.sda.library.domain.model.exception;

public class CategoryDoesNotExistException extends ObjectDoesNotExistException {
    public CategoryDoesNotExistException(Long id) {
        super("Category with id = " + id + " does not exists.");
    }
}
