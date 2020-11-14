package pl.sda.library.infrastructure.jpa;

import org.springframework.data.repository.CrudRepository;
import pl.sda.library.infrastructure.jpa.BookDto;
import pl.sda.library.repository.UserDto;

interface BookDtoRepository extends CrudRepository<BookDto, Long> {

    Iterable<BookDto> findAllByUser(UserDto userDto);
}
