package pl.sda.library.infrastructure.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

interface AuthorDtoRepository extends CrudRepository<AuthorDto, Long> {
    Optional<AuthorDto> findAuthorDtoByFirstNameAndLastName(String firstName, String lastName);
}
