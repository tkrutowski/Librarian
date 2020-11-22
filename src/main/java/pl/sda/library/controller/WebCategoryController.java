package pl.sda.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.sda.library.domain.model.Category;
import pl.sda.library.domain.service.CategoryService;

@AllArgsConstructor
@Controller
@RequestMapping("/categories")
public class WebCategoryController {

    CategoryService categoryService;

    @GetMapping
    public String getAllCategories(Model model) {
        model.addAttribute(new Category());
        model.addAttribute("categoryList", categoryService.findAllCategories());
        return "categories";
    }

    @PostMapping
    public String addCategory(Category category) {
        categoryService.addCategory(category);
        return "redirect:/categories";
    }

    @RequestMapping(value="/delete/{categoryId}",method = RequestMethod.GET)
    public String delCategory(@PathVariable String categoryId) {
        categoryService.deleteCategory(Long.parseLong(categoryId));
        return "redirect:/categories";
    }
}
