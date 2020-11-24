package pl.sda.library.infrastructure.jpa;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.model.Series;
import pl.sda.library.domain.port.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class BookRepositoryAdapter implements BookRepository {

    private BookDtoRepository bookDtoRepository;
    private BookDtoCreator bookDtoCreator;

    @Override
    public Optional<Book> add(Book book) {
        BookDto bookDtoToSave = bookDtoCreator.fromDomain(book);
        BookDto savedBook = bookDtoRepository.save(bookDtoToSave);
        return Optional.of(savedBook.toDomain());
    }

    @Override
    public Optional<Book> edit(Book book) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        bookDtoRepository.deleteById(id);
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        bookDtoRepository.findAll()
                .iterator()
                .forEachRemaining(bookDto -> books.add(bookDto.toDomain()));
        return books;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookDtoRepository.findById(id)
                .map(bookDto -> bookDto.toDomain());
    }

    @Override
    public List<Book> findAllByTitle(String title) {
        List<Book> books = new ArrayList<>();
        bookDtoRepository.findAllByTitle(title)
                .iterator()
                .forEachRemaining(bookDto -> books.add(bookDto.toDomain()));
        return books;
    }

    @Override
    public List<Book> findAllBySeries(Series series) {
        List<Book> books = new ArrayList<>();
        List<BookDto> booksDto = new ArrayList<>();
        bookDtoRepository.findAll()
                .iterator()
                .forEachRemaining(booksDto::add);

         books = booksDto.stream()
                 .filter(bookDto -> bookDto.getSeries().equals(SeriesDto.fromDomain(series)))
                 .map(BookDto::toDomain).collect(Collectors.toList());
        return books;
    }

    @Override
    public Optional<Book> findByTitle(String title) {
        return Optional.empty();
    }
}
