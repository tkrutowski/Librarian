package pl.sda.library.infrastructure.jpa;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.Bookstore;
import pl.sda.library.domain.port.BookstoreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class BookstoreRepositoryAdapter implements BookstoreRepository {

    private BookstoreDtoRepository bookstoreDtoRepository;

    @Override
    public Long add(Bookstore bookstore) {
        return bookstoreDtoRepository.save(BookstoreDto.fromDomain(bookstore)).getIdBookstore();
    }

    @Override
    public Optional<Bookstore> edit(Bookstore bookstore) {
        return Optional.of(bookstoreDtoRepository.save(BookstoreDto.fromDomain(bookstore)).toDomain());
    }

    @Override
    public void delete(Long id) {
        bookstoreDtoRepository.deleteById(id);
    }

    @Override
    public Optional<Bookstore> findById(Long id) {
        return bookstoreDtoRepository.findById(id)
                .map(bookstoreDto -> bookstoreDto.toDomain());
    }

    @Override
    public Optional<Bookstore> findByName(String name) {
        return bookstoreDtoRepository.findBookstoreDtoByName(name)
                .map(bookstoreDto -> bookstoreDto.toDomain());
    }

    @Override
    public List<Bookstore> findAll() {
        List<Bookstore> bookstoreList = new ArrayList<>();
        bookstoreDtoRepository.findAll()
                .iterator()
                .forEachRemaining(bookstoreDto -> bookstoreList.add(bookstoreDto.toDomain()));
        return bookstoreList;
    }
}
