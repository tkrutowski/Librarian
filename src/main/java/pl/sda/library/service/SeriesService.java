package pl.sda.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.library.model.Author;
import pl.sda.library.model.Series;
import pl.sda.library.repository.SeriesRepository;
import pl.sda.library.service.exceptions.ObjectAlreadyExistException;
import pl.sda.library.service.exceptions.ObjectDoesNotExistException;

import java.util.List;

@Service
@AllArgsConstructor
public class SeriesService {

    private SeriesRepository seriesRepository;

    public Long addSeries(Series series){
        if(alreadyExist(series))
            throw new ObjectAlreadyExistException("Podany autor już istnieje w bazie danych.");
        Long id = seriesRepository.addSeries(series);
        return id;
    }

    private boolean alreadyExist(Series series) {
        return seriesRepository.isExist(series.getTitle());
    }

    public List<Series> getAllSerieses(){
        return seriesRepository.getAllSerieses();
    }

    public void delSeries(long id) {
        seriesRepository.deleteSeries(id);
    }

    public Series editSeries(Series series)   {
        Series seriesById = seriesRepository.getSeriesById(series.getIdSeries());
        if(seriesById.getIdSeries() == null)
            throw new ObjectDoesNotExistException("Podany cykl nie istnieje w bazie danych.");

        seriesById.setTitle(series.getTitle());
        seriesById.setDescription(series.getDescription());

        return seriesRepository.editSeries(seriesById);
    }

    public Series getSeries(Long id) {
        Series byId = seriesRepository.getSeriesById(id);
        if(byId.getIdSeries() == null)
            throw new ObjectDoesNotExistException("Podany cykl nie istnieje w bazie danych.");
        else
            return byId;
    }
}
