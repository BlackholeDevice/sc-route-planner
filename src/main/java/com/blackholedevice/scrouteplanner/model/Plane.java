package com.blackholedevice.scrouteplanner.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Plane {
    private Point3D point;
    private Point3D normal;

    public Point3D intersectPlaneLine(Point3D startPoint, Point3D vector){ //return intersection point between two vectors
        var difference = startPoint.subtract(this.point);
        var prod1 = difference.scalarProduct(this.normal);
        var prod2 = vector.scalarProduct(this.normal);
        var prod3 = prod1 / prod2;
        return startPoint.subtract(vector.scale(prod3));
    }
}
