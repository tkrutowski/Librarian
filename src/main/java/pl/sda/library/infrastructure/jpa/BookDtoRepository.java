package pl.sda.library.infrastructure.jpa;

import ch.qos.logback.classic.spi.EventArgUtil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface BookDtoRepository extends JpaRepository<BookDto, Long> {

    Iterable<BookDto> findAllByTitle(String title);

    List<BookDto> findAllByOrderByTitleAsc();
}
