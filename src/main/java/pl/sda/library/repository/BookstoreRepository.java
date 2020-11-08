package pl.sda.library.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.library.model.Bookstore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class BookstoreRepository {

    private BookstoreDtoRepository bookstoreDtoRepository;
    private DtoFactory dtoFactory;

    public Long addBookstore(Bookstore bookstore) {
        Long id = 0L;
        BookstoreDto saved = bookstoreDtoRepository.save(dtoFactory.createBookstoreDto(bookstore));
        id = saved.getIdBookstore();
        return id;
    }

    public List<Bookstore> getAllBookstores() {
        List<Bookstore> bookstoreList = new ArrayList<>();
        bookstoreDtoRepository.findAll().iterator().forEachRemaining(bookstoreDto -> bookstoreList.add(bookstoreDto.toModel()));
        return bookstoreList;
    }

    public void deleteBookstore(long id) {
        bookstoreDtoRepository.deleteById(id);
    }

    public Bookstore getBookstoreById(Long id) {
        Optional<BookstoreDto> byId = bookstoreDtoRepository.findById(id);
        if (byId.isPresent())
            return byId.get().toModel();
        else
            return new Bookstore();
    }

    public Bookstore editBookstore(Bookstore bookstore) {
        BookstoreDto saved = bookstoreDtoRepository.save(dtoFactory.createBookstoreDto(bookstore));
        return saved.toModel();
    }

    public boolean isExist(String name) {
        Optional<BookstoreDto> bookstoreDtoByName = bookstoreDtoRepository.findBookstoreDtoByName(name);
        if (bookstoreDtoByName.isPresent())
            return true;
        else
            return false;
    }
}
