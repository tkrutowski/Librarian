package pl.sda.library.domain.port;

import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.Category;

import java.util.List;
import java.util.Optional;

@Component
public interface CategoryRepository {

    Long addCategory(Category category);

    List<Category> getAllCategories();

    void deleteCategory(long id);

    boolean isExist(String name);

    boolean isExistById(Long id);

    Optional<Category> getCategoryById(Long id);

    Optional<Category> editCategory(Category category);
}
