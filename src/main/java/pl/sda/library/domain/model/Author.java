package pl.sda.library.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Author {
    private Long id;
    private String firstName;
    private String lastName;
}
