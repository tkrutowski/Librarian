package pl.sda.library.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Bookstore {
    private Long idBookstore;
    private String name;
    private String url;
}
