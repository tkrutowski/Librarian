package pl.sda.library.domain.port;

import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.Bookstore;

import java.util.List;
import java.util.Optional;

@Component
interface BookstoreRepository {

    Long addBookstore(Bookstore bookstore);

    List<Bookstore> getAllBookstores();

    void deleteBookstore(long id);

    Optional<Bookstore> getBookstoreById(Long id);

    Optional<Bookstore> editBookstore(Bookstore bookstore);

    boolean isExist(String name);
}
