package com.blackholedevice.scrouteplanner.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Sphere {
    private Point3D center;
    private double radius;

    public Circle intersectSpheres(Sphere s) { //return the intersection circle of 2 spheres
        double distance = this.center.distance(s.center);
        if(this.radius + s.radius < distance){
            return null;
        } else if (this.radius + s.radius == distance){
            return null;
        } else {
            distance = this.center.distance(s.center);
            double h = 0.5 + (Math.pow(this.radius, 2) - Math.pow(s.radius, 2)) / (2 * Math.pow(distance, 2));
            Point3D c_i = this.center.add(s.center.subtract(this.center).scale(h));
            double r_i = Math.sqrt(Math.pow(this.radius, 2) - Math.pow(h, 2) * Math.pow(distance, 2));

            return new Circle(c_i, r_i, this.center.vectorTo(s.center).normalize());
        }
    }

    public Circle intersectSpherePlane(Plane p) { //return the intersection circle of a sphere and a plane
        var distance = p.getNormal().normalize().scalarProduct(p.getPoint().subtract(this.center));
        if(Math.abs(distance) > this.radius){
            return null;
        } else {
            Point3D new_centre = this.center.add(p.getNormal().normalize().invert().scale(distance));
            if(Math.abs(distance) == this.radius){
                return null;
            } else {
                double new_radius = Math.sqrt(Math.pow(this.radius, 2) - Math.pow(distance, 2));
                return new Circle(new_centre, new_radius, p.getNormal());
            }
        }
    }

    public Point3D[] intersectSphereCircle(Circle c) { //return the intersection points of a sphere and a circle
        var distance = c.getNormal().normalize().scalarProduct(this.center.subtract(c.getCenter()));
        if(Math.abs(distance) > this.radius){
            return new Point3D[0];
        } else {
            var new_centre = this.center.add(c.getNormal().normalize().invert().scale(distance));
            if(Math.abs(distance) == this.radius){
                return new Point3D[] {new_centre};
            } else {
                var new_radius = Math.sqrt(Math.pow(this.radius, 2) - Math.pow(distance, 2));
                return c.intersectCircles(new Circle(new_centre, new_radius, c.getNormal()));
            }
        }
    }
}
