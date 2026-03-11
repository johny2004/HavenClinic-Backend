package puj.web.clinicahaven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import puj.web.clinicahaven.dto.DashboardDTO;
import puj.web.clinicahaven.servicio.DashboardService;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = {"http://localhost:4200", "https://johny2004.github.io"})
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/kpis")
    public DashboardDTO getKPIs() {
        return dashboardService.getKPIs();
    }
}