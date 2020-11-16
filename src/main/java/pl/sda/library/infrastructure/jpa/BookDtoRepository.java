package pl.sda.library.infrastructure.jpa;

import org.springframework.data.repository.CrudRepository;

interface BookDtoRepository extends CrudRepository<BookDto, Long> {

    Iterable<BookDto> findAllByTitle(String title);
}
