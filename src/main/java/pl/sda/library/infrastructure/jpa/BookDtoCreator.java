package pl.sda.library.infrastructure.jpa;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.Book;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@AllArgsConstructor
class BookDtoCreator {
    SeriesDtoRepository seriesDtoRepository;
    CategoryDtoRepository categoryDtoRepository;
    BookstoreDtoRepository bookstoreDtoRepository;
    AuthorDtoRepository authorDtoRepository;

    BookDto fromDomain(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setSeries(getSeriesFromString(book.getSeries()));
        bookDto.setAuthors(getAuthorsFromString(book.getAuthors()));
        bookDto.setCategories(getCategoriesFromString(book.getCategories()));
        bookDto.setTitle(book.getTitle());
        bookDto.setDescription(book.getDescription());
        bookDto.setCover(book.getCover());
        bookDto.setBookInSeriesNo(book.getBookInSeriesNo());
        return bookDto;
    }

    private SeriesDto getSeriesFromString(String series) {
        Optional<SeriesDto> seriesDtoByTitle = seriesDtoRepository.findSeriesDtoByTitle(series);
        if (seriesDtoByTitle.isPresent()) {
            return seriesDtoByTitle.get();
        } else {
            SeriesDto seriesDto = new SeriesDto();
            seriesDto.setTitle(series);
            seriesDto.setDescription(series);
            return seriesDtoRepository.save(seriesDto);
        }
    }


    private Set<AuthorDto> getAuthorsFromString(String authors) {
        Set<AuthorDto> authorDtos = new HashSet<>();
        String[] authorsList = authors.trim().split(",");
        for (String author : authorsList) {
            AuthorDto validAuthor = validAuthor(author);
//            String[] authorsSplit = author.trim().split(" ");
            Optional<AuthorDto> authorDtoByFirstNameAndLastName = authorDtoRepository.findAuthorDtoByFirstNameAndLastName(validAuthor.getFirstName(), validAuthor.getLastName());
            if (authorDtoByFirstNameAndLastName.isPresent()) {
                authorDtos.add(authorDtoByFirstNameAndLastName.get());
            } else {
//                AuthorDto newAuthorDto = new AuthorDto();
//                newAuthorDto.setFirstName(authorsSplit[0]);
//                newAuthorDto.setLastName(authorsSplit[1]);
                authorDtos.add(authorDtoRepository.save(validAuthor));
            }
        }
        return authorDtos;
    }

    private Set<CategoryDto> getCategoriesFromString(String categories) {
        Set<CategoryDto> categoryDtos = new HashSet<>();
        String[] categoriesList = categories.trim().split(",");
        for (String category : categoriesList) {
            Optional<CategoryDto> categoryDtoByName = categoryDtoRepository.findCategoryDtoByName(category.trim());
            if (categoryDtoByName.isPresent()) {
                categoryDtos.add(categoryDtoByName.get());
            } else {
                CategoryDto newCategoryDto = new CategoryDto();
                newCategoryDto.setName(category.trim());
                categoryDtos.add(categoryDtoRepository.save(newCategoryDto));
            }
        }
        return categoryDtos;
    }

    private AuthorDto validAuthor(String author) {
        AuthorDto authorDto = new AuthorDto();
        String[] authorsSplit = author.trim().split(" ");

        if (authorsSplit.length == 1) {
            authorDto.setFirstName("");
            authorDto.setLastName(authorsSplit[0]);
        }

        if (authorsSplit.length > 1) {
            authorDto = author.lastIndexOf("-") > 0 ? getAuthorWithSeveralLastNames(author) : getAuthorWithSeveralFirstNames(author);
        }

        return authorDto;
    }

    private AuthorDto getAuthorWithSeveralFirstNames(String author) {
        AuthorDto authorDto = new AuthorDto();
        int i = author.lastIndexOf(" ");
        authorDto.setFirstName(author.substring(0, i).trim());
        authorDto.setLastName(author.substring(i).trim());
        return authorDto;
    }

    private AuthorDto getAuthorWithSeveralLastNames(String author) {
        AuthorDto authorDto = new AuthorDto();
        int i = author.lastIndexOf("-");
        authorDto = (getAuthorWithSeveralFirstNames(author.substring(0, i).trim()));

        authorDto.setLastName(authorDto.getLastName()  + "-" + author.substring(i + 1).trim());
        return authorDto;
    }
}
