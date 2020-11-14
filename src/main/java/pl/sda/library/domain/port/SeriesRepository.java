package pl.sda.library.domain.port;

import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.Series;

import java.util.List;
import java.util.Optional;

@Component
public interface SeriesRepository {

    Long addSeries(Series series);

    List<Series> getAllSerieses();

    void deleteSeries(long id);

    boolean isExist(String title);

    Optional<Series> getSeriesById(Long idSeries);

    Optional<Series> editSeries(Series series);

    boolean isExistById(Long id);
}
