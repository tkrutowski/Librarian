package pl.sda.library.infrastructure.jpa;

import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.Series;
import pl.sda.library.domain.port.SeriesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class SeriesRepositoryAdapter implements SeriesRepository {

    private SeriesDtoRepository seriesDtoRepository;

    @Override
    public Long add(Series series) {
        return seriesDtoRepository.save(SeriesDto.fromDomain(series)).getId();
    }

    @Override
    public Optional<Series> edit(Series series) {
        return Optional.of(seriesDtoRepository.save(SeriesDto.fromDomain(series)).toDomain());
    }

    @Override
    public void delete(Long id) {
        seriesDtoRepository.findById(id)
                .ifPresent(seriesDto -> seriesDtoRepository.delete(seriesDto));
    }

    @Override
    public Optional<Series> findById(Long id) {
        return seriesDtoRepository.findById(id)
                .map(seriesDto -> seriesDto.toDomain());
    }

    @Override
    public Optional<Series> findByTitle(String title) {
        return seriesDtoRepository.findSeriesDtoByTitle(title)
                .map(seriesDto -> seriesDto.toDomain());
    }

    @Override
    public List<Series> findAll() {
        List<Series> seriesList = new ArrayList<>();
        seriesDtoRepository.findAll()
                .iterator()
                .forEachRemaining(seriesDto -> seriesList.add(seriesDto.toDomain()));
        return seriesList;
    }
}
