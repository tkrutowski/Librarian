package pl.sda.library.domain.model.exception;

import pl.sda.library.domain.model.Category;

public class CategoryAleradyExistException extends ObjectAlreadyExistException {
    public CategoryAleradyExistException(Category category) {
        super("Category already exists: " + category.toString());
    }
}
