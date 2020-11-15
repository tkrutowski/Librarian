package pl.sda.library.domain.port;

import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.Category;

import java.util.List;
import java.util.Optional;

@Component
public interface CategoryRepository {
    Long add(Category category);

    Optional<Category> edit(Category category);

    boolean deleteCategory(long id);

    List<Category> findAll();

    Optional<Category> findById(Long id);

    Optional<Category> findByName(String name);
}
