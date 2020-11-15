package pl.sda.library.infrastructure.jpa;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

interface CategoryDtoRepository extends CrudRepository<CategoryDto, Long> {

    Optional<CategoryDto> findCategoryDtoByName(String name);
}
