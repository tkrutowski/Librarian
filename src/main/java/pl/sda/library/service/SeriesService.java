package pl.sda.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.library.model.Series;
import pl.sda.library.repository.SeriesRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class SeriesService {

    private SeriesRepository seriesRepository;

    boolean addSeries(Series series){
        //TODO - sprawdzić przed dodaniem czy jest już w bazie
        seriesRepository.addSeries(series);
        return true;
    }

    List<Series> getAllSerieses(){
        return seriesRepository.getAllSerieses();
    }

    public void delSeries(long id) {
        seriesRepository.deleteSeries(id);
    }
}
