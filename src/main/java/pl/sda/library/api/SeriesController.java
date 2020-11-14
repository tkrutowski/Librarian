package pl.sda.library.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.sda.library.domain.model.Series;
import pl.sda.library.domain.service.SeriesService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/series")
public class SeriesController {
    SeriesService seriesService;

    @GetMapping("/all")
    public List<Series> getAllSeries(){
        return seriesService.getAllSerieses();
    }

    @PostMapping("/add")
    public Long addSeries(@RequestBody Series series)  {
        return seriesService.addSeries(series);
    }

    @PutMapping("/edit")
    public Series editSeries(@RequestBody Series series)  {
        return seriesService.editSeries(series);
    }

    @GetMapping("/{id}")
    public Series getSeries(@PathVariable Long id){
        return seriesService.getSeries(id);
    }

    @DeleteMapping("/{id}")
    public void delSeries(@PathVariable Long id){
        seriesService.delSeries(id);
    }
}
