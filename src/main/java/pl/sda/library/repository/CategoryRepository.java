package pl.sda.library.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.Category;
import pl.sda.library.domain.model.exception.ObjectDoesNotExistException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class CategoryRepository {

    private CategoryDtoRepository categoryDtoRepository;
    private DtoFactory dtoFactory;

    public Long addCategory(Category category) {
        Long id = 0L;
        CategoryDto saved = categoryDtoRepository.save(dtoFactory.createCategoryDto(category));
        id=saved.getIdCategory();
        return id;
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        categoryDtoRepository.findAll().iterator().forEachRemaining(categoryDto -> categoryList.add(categoryDto.toModel()));
        return categoryList;
    }

    public void delCategory(long id) {
        if(isExistById(id))
            categoryDtoRepository.deleteById(id);
        else
            throw new ObjectDoesNotExistException("Nie ma kategorii o takim ID: " + id);
    }

    public boolean isExist(String name) {
        Optional<CategoryDto> categoryDto = categoryDtoRepository.findCategoryDtoByName(name);
        if(categoryDto.isPresent())
            return true;
        else
            return false;
    }

    public boolean isExistById(Long id){
        Optional<CategoryDto> byId = categoryDtoRepository.findById(id);
        if(byId.isPresent())
            return true;
        else
            return false;
    }

    public Category getCategoryById(Long id) {
        Optional<CategoryDto> byId = categoryDtoRepository.findById(id);
        if(byId.isPresent())
            return byId.get().toModel();
        else
            return new Category();
    }

    public Category editCategory(Category category) {
        CategoryDto saved = categoryDtoRepository.save(dtoFactory.createCategoryDto(category));
        return saved.toModel();
    }
}
