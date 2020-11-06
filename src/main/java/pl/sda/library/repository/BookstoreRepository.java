package pl.sda.library.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.library.model.Bookstore;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class BookstoreRepository {

    private BookstoreDtoRepository bookstoreDtoRepository;
    private DtoFactory dtoFactory;

    public boolean addBookstore(Bookstore bookstore) {
        bookstoreDtoRepository.save(dtoFactory.createBookstoreDto(bookstore));
        return true;
    }

    public List<Bookstore> getAllBookstores() {
        List<Bookstore> bookstoreList = new ArrayList<>();
        bookstoreDtoRepository.findAll().iterator().forEachRemaining(bookstoreDto -> bookstoreList.add(bookstoreDto.toModel()));
        return bookstoreList;
    }

    public void deleteBookstore(long id) {
        bookstoreDtoRepository.deleteById(id);
    }
}
