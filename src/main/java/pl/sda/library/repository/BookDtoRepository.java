package pl.sda.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

interface BookDtoRepository extends JpaRepository<BookDto, Long> {
}
