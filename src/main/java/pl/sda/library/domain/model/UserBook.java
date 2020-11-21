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
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        private Date readFrom;
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        private Date readTo;
        private String info;
        private Boolean isRead;
}
