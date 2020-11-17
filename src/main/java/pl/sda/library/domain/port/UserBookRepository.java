package pl.sda.library.domain.port;

import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.UserBook;

import java.util.List;
import java.util.Optional;

@Component
public interface UserBookRepository {

    Optional<UserBook> add(UserBook userBook);

    Optional<UserBook> edit(UserBook userBook);

    void delete(Long id);

    List<UserBook> findAll();

    Optional<UserBook> findById(Long id);

}
