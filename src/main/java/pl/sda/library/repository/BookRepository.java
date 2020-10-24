package pl.sda.library.repository;

import java.util.List;

public abstract class BookRepository implements BookDtoRepository {
    @Override
    public List<BookDto> findAll() {
        return null;
    }
}
