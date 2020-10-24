package pl.sda.library.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {
    private Long idBook;
    private Long idUser;
    private Long idBookstore;
    private Long idSeries;
    private String authors;
    private String categories;
    private String title;
    private String subtitle;
    private String description;
    private String cover;
    private EditionType editionType;
    private ReadingStatus readingStatus;
    private OwnershipStatus ownershipStatus;
    private LocalDate readFrom;
    private LocalDate readTo;
    private String info;
    private boolean isRead;
}
