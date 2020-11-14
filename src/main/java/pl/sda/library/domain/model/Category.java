package pl.sda.library.domain.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Category {
    private Long idCategory;
    private String name;
}
