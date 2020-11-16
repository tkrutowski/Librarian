package pl.sda.library.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.sda.library.domain.model.Series;
import pl.sda.library.domain.service.SeriesService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/series")
public class SeriesController {
    SeriesService seriesService;

    @PostMapping
    public Long addSeries(@RequestBody Series series) {
        return seriesService.addSeries(series);
    }

    @PutMapping("/{id}")
    public Series editSeries(@RequestBody Series series, @PathVariable Long id) {
        return seriesService.editSeries(series, id);
    }

    @DeleteMapping("/{id}")
    public void deleteSeries(@PathVariable Long id) {
        seriesService.deleteSeries(id);
    }

    @GetMapping("/{id}")
    public Series findSeries(@PathVariable Long id) {
        return seriesService.findSeries(id);
    }

    @GetMapping
    public List<Series> findAllSeries() {
        return seriesService.findAllSeries();
    }
}
