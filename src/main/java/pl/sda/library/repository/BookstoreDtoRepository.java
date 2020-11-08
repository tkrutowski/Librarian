package pl.sda.library.repository;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

interface BookstoreDtoRepository extends CrudRepository<BookstoreDto, Long> {
    Optional<BookstoreDto> findBookstoreDtoByName(String name);
}
