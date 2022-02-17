package fi.shednet.networkmonitoring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class NetworkMonitoringController {

    @Autowired
    NetworkMonitoringService networkMonitoringService;
    @GetMapping("/")
    public String index(Model model) {
        String useCase = "default";
        model.addAttribute("results", this.networkMonitoringService.getMonitoringEvents(useCase));
        model.addAttribute("downloadUploadResults", this.networkMonitoringService.getDownloadUpload(useCase));
        model.addAttribute("pingResults", this.networkMonitoringService.getPing(useCase));
        return "index";
    }
    @GetMapping("/all")
    public String all(Model model) {
        String useCase = "all";
        model.addAttribute("results", this.networkMonitoringService.getMonitoringEvents(useCase));
        model.addAttribute("downloadUploadResults", this.networkMonitoringService.getDownloadUpload(useCase));
        model.addAttribute("pingResults", this.networkMonitoringService.getPing(useCase));
        return "all";
    }
    @GetMapping("/page")
    public String index(Model model, @RequestParam("page") Optional<Integer> page) {
        int currentPage = page.orElse(1);
        model.addAttribute("results", this.networkMonitoringService.getPageableMonitoringEvents(currentPage));
        model.addAttribute("downloadUploadResults", this.networkMonitoringService.getPageableDownloadUpload(currentPage));
        model.addAttribute("pingResults", this.networkMonitoringService.getPageablePing(currentPage));
        return "index";
    }
}
