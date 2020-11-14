package pl.sda.library.domain.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Bookstore {
    private Long idBookstore;
    private String name;
    private String www;
}
