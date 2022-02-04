package fi.shednet.networkmonitoring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class NetworkMonitoringController {

    @Autowired
    NetworkMonitoringService networkMonitoringService;
    @GetMapping
    public String index(Model model) {
        model.addAttribute("results", this.networkMonitoringService.getMonitoringEvents());
        return "index";
    }
}
