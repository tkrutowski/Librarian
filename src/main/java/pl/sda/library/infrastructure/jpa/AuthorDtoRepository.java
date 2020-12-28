package pl.sda.library.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface AuthorDtoRepository extends JpaRepository<AuthorDto, Long> {
    Optional<AuthorDto> findAuthorDtoByFirstNameAndLastName(String firstName, String lastName);

    List<AuthorDto> findAllByOrderByLastNameAsc();

}
