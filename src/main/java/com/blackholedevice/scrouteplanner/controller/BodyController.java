package com.blackholedevice.scrouteplanner.controller;

import com.blackholedevice.scrouteplanner.entity.Body;
import com.blackholedevice.scrouteplanner.entity.Location;
import com.blackholedevice.scrouteplanner.repository.BodyRepository;
import com.blackholedevice.scrouteplanner.repository.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bodies")
@CrossOrigin
public class BodyController {

    private final BodyRepository repository;
    private final LocationRepository locationRepository;

    @Autowired
    public BodyController(BodyRepository repository, LocationRepository locationRepository) {
        this.repository = repository;
        this.locationRepository = locationRepository;
    }

    @GetMapping("/{id}")
    public Body getBodyById(@PathVariable long id) {
        return repository.findByIdAndActiveTrue(id);
    }

    @GetMapping("/{id}/locations")
    public List<Location> getLocationsOnBody(@PathVariable long id) {
        return locationRepository.findAllByBodyId(id);
    }
}
