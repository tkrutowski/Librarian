package pl.sda.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.library.domain.service.SeriesService;

@AllArgsConstructor
@Controller
public class WebMainController {

    SeriesService seriesService;

    @GetMapping("/home")
    public ModelAndView getHomePage() {
        ModelAndView modelAndView = new ModelAndView();
        String infoBarText = "Witaj ";
        boolean isLogged = false;
        modelAndView.addObject("seriesList", seriesService.findAllSeries());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null || !authentication.getPrincipal().equals("anonymousUser")) {
            infoBarText += authentication.getName();
            isLogged = true;
        }

        modelAndView.addObject("infoBar", infoBarText);
        modelAndView.addObject("logged", isLogged);
        modelAndView.setViewName("home");
        return modelAndView;
    }
}
