package pl.sda.library.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

interface CategoryDtoRepository extends CrudRepository<CategoryDto, Long> {

    Optional<CategoryDto> findCategoryDtoByName(String name);
}
