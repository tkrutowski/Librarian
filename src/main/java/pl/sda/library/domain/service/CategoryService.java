package pl.sda.library.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.library.domain.model.Category;
import pl.sda.library.domain.model.exception.CategoryAleradyExistException;
import pl.sda.library.domain.model.exception.CategoryDoesNotExistException;
import pl.sda.library.domain.port.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    CategoryRepository categoryRepository;

    public Long addCategory(Category category) {
        Optional<Category> optionalCategory = categoryRepository.findByName(category.getName());
        if (optionalCategory.isPresent()) {
            throw new CategoryAleradyExistException(category);
        }
        return categoryRepository.add(category);
    }

    public Category editCategory(Category categoryToEdit, Long id) {
        Optional<Category> categoryById = categoryRepository.findById(id);
        if (!categoryById.isPresent()) {
            throw new CategoryDoesNotExistException(id);
        }

        categoryById.get().setName(categoryToEdit.getName());

        return categoryRepository.edit(categoryById.get()).get();
    }

    public void deleteCategory(long id) {
        categoryRepository.delete(id);
    }

    public Category findCategory(Long id) {
        Optional<Category> categoryById = categoryRepository.findById(id);
        if (!categoryById.isPresent()) {
            throw new CategoryDoesNotExistException(id);
        }
        return categoryById.get();
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

}
