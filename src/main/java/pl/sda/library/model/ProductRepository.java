package pl.sda.library.model;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {
    List<Book> findByPhrase (String phrase);
}
