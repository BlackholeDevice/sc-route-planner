package com.blackholedevice.scrouteplanner.controller;

import com.blackholedevice.scrouteplanner.entity.Body;
import com.blackholedevice.scrouteplanner.entity.System;
import com.blackholedevice.scrouteplanner.repository.BodyRepository;
import com.blackholedevice.scrouteplanner.repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/systems")
@CrossOrigin
public class SystemController {
    private final SystemRepository repository;
    private final BodyRepository bodyRepository;

    @Autowired
    public SystemController(SystemRepository repository, BodyRepository bodyRepository) {
        this.repository = repository;
        this.bodyRepository = bodyRepository;
    }

    @GetMapping
    public List<System> getActiveSystems() {
        return repository.findAllByActiveTrue();
    }

    @GetMapping("/{id}")
    public System findSystemById(@PathVariable long id) {
        return repository.findByIdAndActiveTrue(id);
    }

    @GetMapping("/{id}/bodies")
    public List<Body> findBodiesInSystem(@PathVariable long id) {
        return bodyRepository.findAllBySystemId(id);
    }
}
