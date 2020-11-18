package pl.sda.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.sda.library.domain.model.Series;
import pl.sda.library.domain.service.SeriesService;

@AllArgsConstructor
@Controller
@RequestMapping("/series")
public class WebSeriesController {
    SeriesService seriesService;

    @GetMapping
    public String getAllSeries(Model model) {
        model.addAttribute(new Series());
        model.addAttribute("seriesList", seriesService.findAllSeries());
        return "series";
    }

    @PostMapping
    public String addCSeries(Series series) {
        seriesService.addSeries(series);
        return "redirect:/series";
    }

    @RequestMapping(value="/delete/{seriesId}",method = RequestMethod.GET)
    public String delSeries(@PathVariable String seriesId) {
        seriesService.deleteSeries(Long.parseLong(seriesId));
        return "redirect:/series";
    }
}
