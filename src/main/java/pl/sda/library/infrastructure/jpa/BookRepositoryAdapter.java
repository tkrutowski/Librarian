package pl.sda.library.infrastructure.jpa;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.Bookstore;
import pl.sda.library.domain.port.BookstoreRepository;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class BookRepositoryAdapter implements BookstoreRepository {

    private BookDtoRepository bookDtoRepository;

    @Override
    public Long add(Bookstore bookstore) {
        return null;
    }

    @Override
    public Optional<Bookstore> edit(Bookstore bookstore) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Bookstore> findAll() {
        return null;
    }

    @Override
    public Optional<Bookstore> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Bookstore> findByName(String name) {
        return Optional.empty();
    }
}
