package com.blackholedevice.scrouteplanner.model;

import com.blackholedevice.scrouteplanner.entity.Location;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Route {
    private Location destination;
    private List<Step> steps;
}
