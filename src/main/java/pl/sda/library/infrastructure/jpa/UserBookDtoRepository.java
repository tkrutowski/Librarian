package pl.sda.library.infrastructure.jpa;

import org.springframework.data.repository.CrudRepository;

interface UserBookDtoRepository extends CrudRepository<UserBookDto, Long> {
}
