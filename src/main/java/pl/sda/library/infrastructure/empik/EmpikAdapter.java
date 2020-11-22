package pl.sda.library.infrastructure.empik;

import pl.sda.library.domain.model.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EmpikAdapter {

    public List<Book> findByPhrase(String phrase) {
        List<EmpikProductDto> dto = adaptItemsFromScrapper(phrase);
        return toDomain(dto);
    }


    public List<EmpikProductDto> adaptItemsFromScrapper(String phrase){
        List<String> listFromScrapper = EmpikScrapper.finder(phrase);
        List<EmpikProductDto> dtos = new ArrayList<>();
        List<String> parts;

        for (String itemFromScrapper : listFromScrapper){
            EmpikProductDto product = new EmpikProductDto();
            parts = Arrays.asList(itemFromScrapper.trim().split("::"));
            product.setBookstore(parts.get(0));
            product.setAuthors(parts.get(1));
            product.setTitle(parts.get(2));
            //product.setDescription(parts.get(3));
            //product.setCover(parts.get(4));
            //product.setEditionType(parts.get(5));
            //product.setIsbn(parts.get(6));
            dtos.add(product);
        }
        return  dtos;
    }

    private List<Book> toDomain(List<EmpikProductDto> dto) {
        return dto.stream()
                .map(EmpikProductDto::toDomain)
                .collect(Collectors.toList());
    }
}
