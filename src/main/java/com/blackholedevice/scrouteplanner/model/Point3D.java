package com.blackholedevice.scrouteplanner.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Point3D {
    public static final Point3D MAX = new Point3D(100000, 100000, 1000000);
    public static final Point3D ORIGIN = new Point3D(0, 0, 0);
    private final double x;
    private final double y;
    private final double z;

    public Point3D subtract(Point3D p) { //vector subtraction
        return new Point3D(this.x - p.x, this.y - p.y, this.z - p.z);
    }

    public Point3D add(Point3D p) { //vector addition
        return new Point3D(this.x + p.x, this.y + p.y, this.z + p.z);
    }

    public Point3D invert() { //invert vector
        return new Point3D(-this.x, -this.y, -this.z);
    }

    public double distance(Point3D p) { //distance between two points
        return Math.sqrt(Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2) + Math.pow(this.z - p.z, 2));
    }

    public double magnitude() { //length of vector
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
    }

    public Point3D normalize() { //set vector length to 1
        var length = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
        return new Point3D(this.x / length, this.y / length, this.z / length);
    }

    public Point3D scale(double s) { //scale the vector by scalar
        return new Point3D(this.x * s, this.y * s, this.z * s);
    }

    public Point3D crossProduct(Point3D p) { //crossproduct of two vectors
        return new Point3D(this.y * p.z - p.y * this.z, this.z * p.x - p.z * this.x, this.x * p.y - p.x * this.y);
    }

    public Point3D vectorTo(Point3D p) { //vector from one point to the other
        return this.subtract(p).invert();
    }

    public double scalarProduct(Point3D p) { //dotproduct of two vectors
        return (this.x * p.x + this.y * p.y + this.z * p.z);
    }

    public Point3D intersectLines(Point3D v1, Point3D p, Point3D v2) { //check if two lines intersect and return intersection point
        Point3D vector1 = v1.normalize();
        Point3D vector2 = v2.normalize();
        double scalar = (p.subtract(this).crossProduct(vector2)).magnitude() / (vector1.crossProduct(vector2)).magnitude();

        return this.add(vector1.scale(scalar));
    }
}
