package pl.sda.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.library.domain.service.SeriesService;

@AllArgsConstructor
@Controller
//@RequestMapping("/")
public class WebMainController {

    SeriesService seriesService;

    @GetMapping("/home")
    public ModelAndView getHomePage() {
        ModelAndView modelAndView = new ModelAndView();
        String infoBarText = "Coś tutaj będzie!!!";
        modelAndView.addObject("seriesList", seriesService.findAllSeries());
        modelAndView.addObject("infoBar", infoBarText);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      //  String name = authentication.getName();
       // modelAndView.addObject("name", name);
        modelAndView.setViewName("home");
        return modelAndView;
    }
}
