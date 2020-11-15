package pl.sda.library.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.library.domain.model.Series;
import pl.sda.library.domain.model.exception.SeriesAlreadyExistException;
import pl.sda.library.domain.model.exception.SeriesDoesNotExistException;
import pl.sda.library.domain.port.SeriesRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SeriesService {

    private SeriesRepository seriesRepository;

    public Long addSeries(Series series) {
        Optional<Series> optionalSeries = seriesRepository.findByTitle(series.getTitle());
        if (optionalSeries.isPresent()) {
            throw new SeriesAlreadyExistException(series);
        }
        return seriesRepository.add(series);
    }

    public Series editSeries(Series series, Long id) {
        Optional<Series> seriesById = seriesRepository.findById(id);
        if (!seriesById.isPresent()) {
            throw new SeriesDoesNotExistException(id);
        }
        seriesById.get().setTitle(series.getTitle());
        seriesById.get().setDescription(series.getDescription());

        return seriesRepository.edit(seriesById.get()).get();
    }

    public void deleteSeries(Long id) {
        seriesRepository.delete(id);
    }

    public Series findSeries(Long id) {
        Optional<Series> seriesOptional = seriesRepository.findById(id);
        if (!seriesOptional.isPresent()) {
            throw new SeriesDoesNotExistException(id);
        }
        return seriesOptional.get();
    }

    public List<Series> findAllSerieses() {
        return seriesRepository.findAll();
    }
}
