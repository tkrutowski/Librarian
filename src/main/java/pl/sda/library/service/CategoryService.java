package pl.sda.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.library.model.Category;
import pl.sda.library.repository.CategoryRepository;
import pl.sda.library.service.exceptions.ObjectAlreadyExistException;
import pl.sda.library.service.exceptions.ObjectDoesNotExistException;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {


    private CategoryRepository categoryRepository;

    public Long addCategory(Category category) {
        if(alreadyExist(category))
            throw new ObjectAlreadyExistException("Podana categoria już istnieje w bazie danych.");
        Long id = categoryRepository.addCategory(category);
        return id;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    public void delCategory(long id) {
        categoryRepository.delCategory(id);
    }

    private boolean alreadyExist(Category category) {
        return categoryRepository.isExist(category.getName());
    }

    public Category getCategory(Long id) {
        return categoryRepository.getCategoryById(id);
    }

    public Category editCategory(Category category) {
        Category categoryById = categoryRepository.getCategoryById(category.getIdCategory());
        if(categoryById.getIdCategory() == null)
            throw new ObjectDoesNotExistException("Podana kategoria nie istnieje w bazie danych.");

        categoryById.setName(category.getName());

        return categoryRepository.editCategory(categoryById);
    }
}
