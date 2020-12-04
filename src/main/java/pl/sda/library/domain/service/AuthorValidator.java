package pl.sda.library.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.sda.library.domain.model.Author;
import pl.sda.library.domain.model.exception.AuthorDoesNotExistException;

@Component
@AllArgsConstructor
public class AuthorValidator implements Validator {

    private AuthorService authorService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Author.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Author author = (Author) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty","Pole nie może być puste.");

        if (!errors.hasErrors()) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty", "Pole nie może być puste.");
        }

        if (!errors.hasErrors()) {
            try {
                authorService.findAuthor(author.getFirstName(),author.getLastName());
                errors.rejectValue("lastName", "Duplicate.bookstoreForm.name", "Autor istnieje już w bazie danych");
            } catch (AuthorDoesNotExistException ignored) {

            }
        }
    }
}
