package pl.sda.library.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.library.model.Series;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class SeriesRepository {

    private SeriesDtoRepository seriesDtoRepository;
    private DtoFactory dtoFactory;

    public boolean addSeries(Series series) {
        seriesDtoRepository.save(dtoFactory.createSeriesDto(series));
        return true;
    }

    public List<Series> getAllSerieses() {
        List<Series> seriesList = new ArrayList<>();
        seriesDtoRepository.findAll().iterator().forEachRemaining(seriesDto -> seriesList.add(seriesDto.toModel()));
        return seriesList;
    }

    public void deleteSeries(long id) {
        seriesDtoRepository.deleteById(id);
    }
}
