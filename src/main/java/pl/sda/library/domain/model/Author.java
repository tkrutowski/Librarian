package pl.sda.library.domain.model;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Author {
    private Long id;
    private String firstName;
    private String lastName;
}
