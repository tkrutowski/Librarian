package pl.sda.library.infrastructure.jpa;

import org.springframework.data.repository.CrudRepository;
import pl.sda.library.infrastructure.jpa.BookstoreDto;

import java.util.Optional;

interface BookstoreDtoRepository extends CrudRepository<BookstoreDto, Long> {
    Optional<BookstoreDto> findBookstoreDtoByName(String name);
}
