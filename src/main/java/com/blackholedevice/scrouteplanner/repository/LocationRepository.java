package com.blackholedevice.scrouteplanner.repository;

import com.blackholedevice.scrouteplanner.entity.Location;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LocationRepository extends CrudRepository<Location, Long> {
    Location findByIdAndActiveTrue(Long id);

    List<Location> findAllByBodyId(long id);
}
