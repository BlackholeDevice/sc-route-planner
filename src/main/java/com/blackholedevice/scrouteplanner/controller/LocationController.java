package com.blackholedevice.scrouteplanner.controller;

import com.blackholedevice.scrouteplanner.model.Route;
import com.blackholedevice.scrouteplanner.service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locations")
@CrossOrigin
public class LocationController {
    private final NavigationService navigationService;

    @Autowired
    public LocationController(NavigationService navigationService) {
        this.navigationService = navigationService;
    }

    @GetMapping("/{id}/navigate")
    public Route navigate(@PathVariable("id") long locationId) {
        return navigationService.navigate(locationId);
    }
}
