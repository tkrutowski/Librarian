package pl.sda.library.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.sda.library.domain.model.Book;

@Component
@AllArgsConstructor
public class BookValidator implements Validator {

    BookService bookService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;

        if (bookService.isBookExist(book)) {
            errors.rejectValue("title", "Duplicate.bookForm.title", "Książka istnieje już w bazie danych.");
        }

        if (!errors.hasErrors()) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty", "Pole nie może być puste");
        }
        if (!errors.hasErrors()) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "authors", "NotEmpty", "Pole nie może być puste");
        }

        if (!errors.hasErrors()) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categories", "NotEmpty", "Pole nie może być puste");
        }
    }
}
