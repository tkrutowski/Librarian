package pl.sda.library.infrastructure.upolujebooka;

import pl.sda.library.domain.model.Book;
//import pl.sda.library.model.ProductRepositoryory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UpolujebookaAdapter {

    //@Override
    public List<Book> findByPhrase(String phrase) {
        List<UpolujebookaProductDto> dto = adaptItemsFromScrapper(phrase);
        return toDomain(dto);
    }


    public List<UpolujebookaProductDto> adaptItemsFromScrapper(String phrase){
        List<String> listFromScrapper = UpolujebookaScrapper.finder(phrase);
        List<UpolujebookaProductDto> dtos = new ArrayList<>();
        List<String> parts;

        for (String itemFromScrapper : listFromScrapper){
            UpolujebookaProductDto product = new UpolujebookaProductDto();
            parts = Arrays.asList(itemFromScrapper.trim().split("::"));
            product.setBookstore(parts.get(0));
            product.setAuthors(parts.get(1));
            product.setTitle(parts.get(2));
            product.setDescription(parts.get(3));
            product.setCover(parts.get(4));
            product.setEditionType(parts.get(5));
            product.setIsbn(parts.get(6));
            dtos.add(product);
        }
        return  dtos;
    }

    private List<Book> toDomain(List<UpolujebookaProductDto> dto) {
        return dto.stream()
                .map(UpolujebookaProductDto::toDomain)
                .collect(Collectors.toList());
    }
}
