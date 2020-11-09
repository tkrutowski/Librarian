package pl.sda.library.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.library.model.Author;
import pl.sda.library.model.Series;
import pl.sda.library.service.exceptions.ObjectDoesNotExistException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class SeriesRepository {

    private SeriesDtoRepository seriesDtoRepository;
    private DtoFactory dtoFactory;

    public Long addSeries(Series series) {
        Long id = 0L;
        SeriesDto saved =  seriesDtoRepository.save(dtoFactory.createSeriesDto(series));
        id=saved.getIdSeries();
        return id;
    }

    public List<Series> getAllSerieses() {
        List<Series> seriesList = new ArrayList<>();
        seriesDtoRepository.findAll().iterator().forEachRemaining(seriesDto -> seriesList.add(seriesDto.toModel()));
        return seriesList;
    }

    public void deleteSeries(long id) {
        if(isExistById(id))
            seriesDtoRepository.deleteById(id);
        else
            throw new ObjectDoesNotExistException("Nie ma autora o takim ID: " + id);

    }

    public boolean isExist(String title) {
        Optional<SeriesDto> seriesDto = seriesDtoRepository.findSeriesDtoByTitle(title);
        if(seriesDto.isPresent())
            return true;
        else
            return false;
    }

    public Series getSeriesById(Long idSeries) {
        Optional<SeriesDto> byId = seriesDtoRepository.findById(idSeries);
        if(byId.isPresent())
            return byId.get().toModel();
        else
            return new Series();
    }

    public Series editSeries(Series series) {
        SeriesDto seriesDto = seriesDtoRepository.save(dtoFactory.createSeriesDto(series));
        return seriesDto.toModel();
    }

    public boolean isExistById(Long id){
        Optional<SeriesDto> byId = seriesDtoRepository.findById(id);
        if(byId.isPresent())
            return true;
        else
            return false;
    }
}
