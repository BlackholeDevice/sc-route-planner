package com.blackholedevice.scrouteplanner.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Circle {
    private Point3D center;
    private double radius;
    private Point3D normal;

    public boolean isPointInCircle(Point3D p){ //check if point is inside the cylinder with the base of the circle
        return new Plane(this.center, this.center).intersectPlaneLine(p, this.normal).distance(this.center) <= this.radius;
    }

    public Point3D[] intersectCircles(Circle c){ //return the intersection between 2 circles in the same plane in 3d space
        double distance = this.center.distance(c.center);
        Point3D t = this.center.subtract(c.center).crossProduct(this.normal).normalize();
        if(this.radius + c.radius < distance){
            return new Point3D[]{};
        } else if(this.radius + c.radius == distance){
            return new Point3D[] {this.center.vectorTo(c.center).normalize().scale(this.radius)};
        } else {
            var h = 0.5 + (Math.pow(this.radius, 2) - Math.pow(c.radius, 2)) / (2 * Math.pow(this.center.distance(c.center), 2));
            var c_i = this.center.add(c.center.subtract(this.center).scale(h));
            var r_i = Math.sqrt(Math.pow(this.radius, 2) - Math.pow(h, 2) * Math.pow(this.center.distance(c.center), 2));
            return new Point3D[] {c_i.subtract(t.scale(r_i)), c_i.add(t.scale(r_i))};
        }
    }
}
