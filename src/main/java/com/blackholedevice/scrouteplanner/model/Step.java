package com.blackholedevice.scrouteplanner.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Step {
    private int marker;
    private double distance;
}
