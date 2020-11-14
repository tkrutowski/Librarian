package pl.sda.library.domain.port;

import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.User;

import java.util.List;
import java.util.Optional;

@Component
public interface UserRepository {

    Long addUser(User user);

    List<User> getAllUsers();

    void deleteUser(long id);

    Optional<User> getUserById(Long idUser);

    boolean isExistById(Long id);

    boolean isExist(String login);

    Optional<User> editUser(User user);
}
