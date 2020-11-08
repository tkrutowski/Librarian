package pl.sda.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

interface BookDtoRepository extends CrudRepository<BookDto, Long> {

    Iterable<BookDto> findAllByUser(UserDto userDto);
}
