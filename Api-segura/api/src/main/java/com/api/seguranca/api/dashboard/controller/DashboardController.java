package com.api.seguranca.api.dashboard.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.api.seguranca.api.dashboard.dto.DashboardDTO; 
import com.api.seguranca.api.dashboard.service.DashboardService;


@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<DashboardDTO> getDashboard() {
        return ResponseEntity.ok(service.getDashboard());
    }
}