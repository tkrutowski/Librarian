package pl.sda.library.domain.port;

import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.User;

import java.util.List;
import java.util.Optional;

@Component
public interface UserRepository {

    Long add(User user);

    Optional<User> edit(User user);

    void delete(Long id);

    List<User> findAll();

    Optional<User> findById(Long id);

}
