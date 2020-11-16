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
        bookDto.setId(book.getIdBook());
        bookDto.setSeries(getSeriesFromString(book.getSeries()));
        bookDto.setAuthors(getAuthorsFromString(book.getAuthors()));
        bookDto.setCategories(getCategoriesFromString(book.getCategories()));
        bookDto.setTitle(book.getTitle());
        bookDto.setSubtitle(book.getSubtitle());
        bookDto.setDescription(book.getDescription());
        bookDto.setCover(book.getCover());
        bookDto.setBookInSeriesNo(book.getBookInSeriesNo());
        return bookDto;
    }

    private SeriesDto getSeriesFromString(String series) {
        Optional<SeriesDto> seriesDtoByTitle = seriesDtoRepository.findSeriesDtoByTitle(series);
        if (seriesDtoByTitle.isPresent())
            return seriesDtoByTitle.get();
        return null;
    }

    private Set<AuthorDto> getAuthorsFromString(String authors) {
        Set<AuthorDto> authorDtos = new HashSet<>();
        String[] authorsList = authors.trim().split(",");
        for (String author : authorsList) {
            String[] authorsSplit = author.trim().split(" ");
            Optional<AuthorDto> authorDtoByFirstNameAndLastName = authorDtoRepository.findAuthorDtoByFirstNameAndLastName(authorsSplit[0], authorsSplit[1]);
            if (authorDtoByFirstNameAndLastName.isPresent())
                authorDtos.add(authorDtoByFirstNameAndLastName.get());
        }
        return authorDtos;
    }

    private Set<CategoryDto> getCategoriesFromString(String categories) {
        Set<CategoryDto> categoryDtos = new HashSet<>();
        String[] categoriesList = categories.trim().split(",");
        for (String category : categoriesList) {
            Optional<CategoryDto> categoryDtoByName = categoryDtoRepository.findCategoryDtoByName(category.trim());
            if (categoryDtoByName.isPresent())
                categoryDtos.add(categoryDtoByName.get());
        }
        return categoryDtos;
    }
}
