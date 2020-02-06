package com.blackholedevice.scrouteplanner.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrbitalMarker {
    private Point3D location;
    private int id;
}
