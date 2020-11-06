package pl.sda.library.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.library.model.Category;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class CategoryRepository {

    private CategoryDtoRepository categoryDtoRepository;
    private DtoFactory dtoFactory;

    public boolean addCategory(Category category) {
        categoryDtoRepository.save(dtoFactory.createCategoryDto(category));
        return true;
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        categoryDtoRepository.findAll().iterator().forEachRemaining(categoryDto -> categoryList.add(categoryDto.toModel()));
        return categoryList;
    }

    public void delCategory(long id) {
        categoryDtoRepository.deleteById(id);
    }
}
