package pl.sda.library.infrastructure.jpa;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.model.Bookstore;
import pl.sda.library.domain.model.Category;
import pl.sda.library.domain.model.Series;
import pl.sda.library.domain.model.User;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class DtoFactory {

    private UserDtoRepository userRepository;
    private BookstoreDtoRepository bookstoreRepository;
    private SeriesDtoRepository seriesRepository;
    private AuthorDtoRepository authorDtoRepository;
    private CategoryDtoRepository categoryDtoRepository;

    public BookDto createBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setIdBook(book.getIdBook());
        bookDto.setUser(getUserFromString(book.getUserLogin()));
        bookDto.setBookstore(getBookstoreFromString(book.getBookstore()));
        bookDto.setSeries(getSeriesFromString(book.getSeries()));
        bookDto.setAuthors(getAuthorsFromString(book.getAuthors()));
        bookDto.setCategories(getCategoriesFromString(book.getCategories()));
        bookDto.setTitle(book.getTitle());
        bookDto.setSubtitle(book.getSubtitle());
        bookDto.setDescription(book.getDescription());
        bookDto.setCover(book.getCover());
        bookDto.setEditionType(book.getEditionType());
        bookDto.setReadingStatus(book.getReadingStatus());
        bookDto.setOwnershipStatus(book.getOwnershipStatus());
        bookDto.setReadFrom(book.getReadFrom());
        bookDto.setReadTo(book.getReadTo());
        bookDto.setInfo(book.getInfo());
        bookDto.setIsRead(book.getIsRead());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setVolume(book.getVolume());
        return bookDto;
    }

    public UserDto createUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setIdUser(user.getIdUser());
        userDto.setName(user.getName());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setIsAdmin(user.getIsAdmin());
        return userDto;
    }

    private Set<AuthorDto> getAuthorsFromString(String authors) {
        Set<AuthorDto> authorDtos = new HashSet<>();
        String[] authorsList = authors.trim().split(",");
        for (String author : authorsList) {
            String[] authorsSplit = author.trim().split(" ");
            Optional<AuthorDto> authorDtoByFirstNameAndLastName = authorDtoRepository.findAuthorDtoByFirstNameAndLastName(authorsSplit[0], authorsSplit[1]);
            if (authorDtoByFirstNameAndLastName.isPresent())
                authorDtos.add(authorDtoByFirstNameAndLastName.get());
            else
                authorDtos.add(authorDtoRepository.save(new AuthorDto(null, authorsSplit[0], authorsSplit[1])));
        }
        return authorDtos;
    }

    private SeriesDto getSeriesFromString(String series) {
        Optional<SeriesDto> seriesDtoByTitle = seriesRepository.findSeriesDtoByTitle(series);
        if (seriesDtoByTitle.isPresent())
            return seriesDtoByTitle.get();
        return new SeriesDto();
    }

    private BookstoreDto getBookstoreFromString(String bookstore) {
        Optional<BookstoreDto> bookstoreDtoByName = bookstoreRepository.findBookstoreDtoByName(bookstore);
        if (bookstoreDtoByName.isPresent())
            return bookstoreDtoByName.get();
        return new BookstoreDto();
    }

    private UserDto getUserFromString(String userLogin) {
        Optional<UserDto> userDtoByLogin = userRepository.findUserDtoByLogin(userLogin);
        if (userDtoByLogin.isPresent())
            return userDtoByLogin.get();

        return new UserDto();
    }

    private Set<CategoryDto> getCategoriesFromString(String categories) {

        Set<CategoryDto> categoryDtos = new HashSet<>();
        String[] categoriesList = categories.trim().split(",");
        for (String category : categoriesList) {
            Optional<CategoryDto> categoryDtoByName = categoryDtoRepository.findCategoryDtoByName(category);
            if (categoryDtoByName.isPresent())
                categoryDtos.add(categoryDtoByName.get());
            else
                categoryDtos.add(categoryDtoRepository.save(new CategoryDto(null, category)));
        }
        return categoryDtos;
    }



    public CategoryDto createCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setIdCategory(category.getIdCategory());
        categoryDto.setName(category.getName());
        return categoryDto;
    }


}
