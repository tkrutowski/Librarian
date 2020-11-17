package pl.sda.library.infrastructure.jpa;

import org.springframework.data.repository.CrudRepository;

public interface UserBookDtoRepository extends CrudRepository<UserBookDto, Long> {
}
