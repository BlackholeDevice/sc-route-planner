package com.blackholedevice.scrouteplanner.repository;

import com.blackholedevice.scrouteplanner.entity.Body;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BodyRepository extends CrudRepository<Body, Long> {
    Body findByIdAndActiveTrue(long id);

    List<Body> findAllBySystemId(long systemId);
}
