package com.blackholedevice.scrouteplanner.repository;

import com.blackholedevice.scrouteplanner.entity.System;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SystemRepository extends CrudRepository<System, Long> {
    System findByIdAndActiveTrue(Long id);
    List<System> findAllByActiveTrue();
}
