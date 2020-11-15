package pl.sda.library.infrastructure.jpa;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.library.domain.model.Category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "categories")
class CategoryDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private Long idCategory;
    private String name;

    public Category toDomain() {
        Category category = new Category();
        category.setIdCategory(getIdCategory());
        category.setName(getName());
        return category;
    }

    public CategoryDto fromDomain(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setIdCategory(category.getIdCategory());
        categoryDto.setName(category.getName());
        return categoryDto;
    }
}
