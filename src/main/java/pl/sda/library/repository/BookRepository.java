package pl.sda.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookDto, Long> {
}
