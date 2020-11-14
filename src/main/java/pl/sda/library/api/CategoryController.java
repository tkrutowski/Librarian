package pl.sda.library.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.sda.library.domain.model.Category;
import pl.sda.library.domain.service.CategoryService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

    CategoryService categoryService;

    @GetMapping("/all")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PostMapping("/add")
    public Long addCategory(@RequestBody Category category)  {
        return categoryService.addCategory(category);
    }

    @PutMapping("/edit")
    public Category editCategory(@RequestBody Category category)  {
        return categoryService.editCategory(category);
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Long id){
        return categoryService.getCategory(id);
    }

    @DeleteMapping("/{id}")
    public void delCategory(@PathVariable Long id){
        categoryService.delCategory(id);
    }
}
