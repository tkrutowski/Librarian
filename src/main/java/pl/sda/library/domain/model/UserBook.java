package pl.sda.library.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserBook {
        private Long id;
        private Long idBook;
        private Long idUser;
        private String bookstore;
        private EditionType editionType;
        private ReadingStatus readingStatus;
        private OwnershipStatus ownershipStatus;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate readFrom;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate readTo;
        private String info;
        private Boolean isRead;
}
