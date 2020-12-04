package pl.sda.library.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.sda.library.domain.model.User;
import pl.sda.library.domain.model.exception.UserDoesNotExistException;

@Component
@AllArgsConstructor
public class UserValidator implements Validator {

    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty", "Pole nie może być puste");

        if (!errors.hasErrors()) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty", "Pole nie może być puste");
        }

        if (!errors.hasErrors()) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty", "Pole nie może być puste");
        }

        if (!errors.hasErrors()) {
            try {
                userService.findUserByUserName(user.getUsername());
                errors.rejectValue("username", "Duplicate.userForm.username", "Użytkownik istnieje już w bazie danych.");
            } catch (UserDoesNotExistException ignored) {
            }
        }

        if (!errors.hasErrors()) {
            if (!user.getPasswordConfirm().equals(user.getPassword())) {
                errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm", "Hasła muszą być identyczne.");
            }
        }
    }
}
