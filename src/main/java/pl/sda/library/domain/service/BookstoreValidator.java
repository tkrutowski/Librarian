package pl.sda.library.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.sda.library.domain.model.Bookstore;
import pl.sda.library.domain.model.exception.BookstoreDoesNotExistException;

@Component
@AllArgsConstructor
public class BookstoreValidator implements Validator {

    private BookstoreService bookstoreService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Bookstore.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Bookstore bookstore = (Bookstore) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty","Pole nie może być puste.");

        if (!errors.hasErrors()) {
            try {
                bookstoreService.findBookstoreByName(bookstore.getName());
                errors.rejectValue("name", "Duplicate.bookstoreForm.name", "Księgarnia istnieje już w bazie danych");
            } catch (BookstoreDoesNotExistException ignored) {

            }
        }

    }
}
