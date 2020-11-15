package pl.sda.library.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserBook {
        private Long idUserBook;
        private Long idBook;
        private String userLogin;
        private String bookstore;
        private EditionType editionType;
        private ReadingStatus readingStatus;
        private OwnershipStatus ownershipStatus;
        private LocalDate readFrom;
        private LocalDate readTo;
        private String info;
        private Boolean isRead;
}