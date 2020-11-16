package pl.sda.library.infrastructure.jpa;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.Category;
import pl.sda.library.domain.port.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CategoryRepositoryAdapter implements CategoryRepository {
    private final CategoryDtoRepository categoryDtoRepository;

    @Override
    public Long add(Category category) {
        return categoryDtoRepository.save(CategoryDto.fromDomain(category)).getId();
    }

    @Override
    public Optional<Category> edit(Category category) {
        return Optional.of(categoryDtoRepository.save(CategoryDto.fromDomain(category)).toDomain());
    }

    @Override
    public void delete(Long id) {

        categoryDtoRepository.findById(id)
                .ifPresent(categoryDto -> categoryDtoRepository.delete(categoryDto));
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryDtoRepository.findById(id)
                .map(categoryDto -> categoryDto.toDomain());
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryDtoRepository.findCategoryDtoByName(name)
                .map(categoryDto -> categoryDto.toDomain());
    }

    @Override
    public List<Category> findAll() {
        List<Category> categoryList = new ArrayList<>();
        categoryDtoRepository
                .findAll()
                .iterator()
                .forEachRemaining(categoryDto -> categoryList.add(categoryDto.toDomain()));
        return categoryList;
    }
}
