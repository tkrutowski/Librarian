package pl.sda.library.domain.port;

import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.Bookstore;

import java.util.List;
import java.util.Optional;

@Component
public interface BookstoreRepository {

    Long add(Bookstore bookstore);

    Optional<Bookstore> edit(Bookstore bookstore);

    boolean delete(Long id);

    List<Bookstore> findAll();

    Optional<Bookstore> findById(Long id);

    Optional<Bookstore> findByName(String name);
}
