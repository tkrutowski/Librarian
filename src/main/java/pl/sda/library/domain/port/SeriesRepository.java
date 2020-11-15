package pl.sda.library.domain.port;

import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.Series;

import java.util.List;
import java.util.Optional;

@Component
public interface SeriesRepository {

    Long add(Series series);

    Optional<Series> edit(Series series);

    boolean delete(Long id);

    Optional<Series> getById(Long idSeries);

    Optional<Series> findByName(String name);

    List<Series> getAll();
}
