package pl.sda.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.library.domain.service.SeriesService;

@AllArgsConstructor
@Controller
@RequestMapping("/")
public class WebMainController {

    SeriesService seriesService;
    @GetMapping
    public String getStart(Model model){
        String infoBarText = "Coś tutaj będzie!!!";
        model.addAttribute("seriesList", seriesService.findAllSeries());
        model.addAttribute("infoBar", infoBarText);
        return "index";
    }
}
