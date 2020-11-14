package pl.sda.library.infrastructure.jpa;

import org.springframework.data.repository.CrudRepository;
import pl.sda.library.infrastructure.jpa.CategoryDto;

import java.util.Optional;

interface CategoryDtoRepository extends CrudRepository<CategoryDto, Long> {

    Optional<CategoryDto> findCategoryDtoByName(String name);
}
