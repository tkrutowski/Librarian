package pl.sda.library.repository;

import org.springframework.data.repository.CrudRepository;

interface BookDtoRepository extends CrudRepository<BookDto, Long> {

    Iterable<BookDto> findAllByUser(UserDto userDto);
}
