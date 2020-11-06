package pl.sda.library.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.library.model.Category;
import pl.sda.library.repository.CategoryRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {


    @Autowired //TODO - usunąć
    private CategoryRepository categoryRepository;

    public boolean addCategory(Category category) {
        //TODO - sprawdzić przed dodaniem czy nie istnieje jużw bazie
        categoryRepository.addCategory(category);
        return true;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    public void delCategory(long id) {
        categoryRepository.delCategory(id);
    }
}
