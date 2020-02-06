package com.blackholedevice.scrouteplanner.service;

import com.blackholedevice.scrouteplanner.entity.Location;
import com.blackholedevice.scrouteplanner.model.Plane;
import com.blackholedevice.scrouteplanner.model.Point3D;
import com.blackholedevice.scrouteplanner.model.Route;
import com.blackholedevice.scrouteplanner.model.Sphere;
import com.blackholedevice.scrouteplanner.model.Step;
import com.blackholedevice.scrouteplanner.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class NavigationService {
    private final LocationRepository locationRepository;

    @Autowired
    public NavigationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Route navigate(long locationId) {
        Location destination = this.locationRepository.findByIdAndActiveTrue(locationId);
        Route route = new Route();
        route.setDestination(destination);
        route.setSteps(getRouteSteps(destination));
        return route;
    }

    private List<Integer> getRouteMarkers(Point3D destination, List<Point3D> orbitalMakers) {
        int marker;
        return Arrays.asList(
                (marker = getStartingPoint(destination, orbitalMakers)),
                (marker = getClosestMarker(destination, marker, orbitalMakers)),
                (marker = getClosestMarker(destination, marker, orbitalMakers)),
                (getClosestMarker(destination, marker, orbitalMakers))
        );
    }

    public List<Step> getRouteSteps(Location destination) {
        List<Point3D> orbitalMarkers = getOrbitalMakers(destination.getBody().getOmRadius());
        List<Integer> routeMarkers = getRouteMarkers(destination.toPoint(), orbitalMarkers);
        Point3D firstPoint = orbitalMarkers.get(routeMarkers.get(0));
        Point3D secondPoint = orbitalMarkers.get(routeMarkers.get(1));
        Point3D thirdPoint = orbitalMarkers.get(routeMarkers.get(2));
        Plane intersectingPlane = new Plane(firstPoint, firstPoint.vectorTo(secondPoint).crossProduct(firstPoint.vectorTo(thirdPoint)));
        if(destination.getBody().toSphere().intersectSpherePlane(intersectingPlane).isPointInCircle(destination.toPoint())) {
            return getRouteStepsIfPointInCircle(destination.toPoint(), routeMarkers, orbitalMarkers);
        } else {
            return getRouteStepsIfPointNotInCircle(destination, routeMarkers, orbitalMarkers);
        }
    }

    private List<Step> getRouteStepsIfPointInCircle(Point3D destination, List<Integer> routeMarkers, List<Point3D> orbitalMarkers) {
        Point3D firstPoint = orbitalMarkers.get(routeMarkers.get(0));
        Point3D secondPoint = orbitalMarkers.get(routeMarkers.get(1));
        Point3D thirdPoint = orbitalMarkers.get(routeMarkers.get(2));
        Point3D fourthPoint = orbitalMarkers.get(routeMarkers.get(3));

        Point3D secondTurn = new Plane(
                firstPoint,
                firstPoint
                        .vectorTo(secondPoint)
                        .crossProduct(firstPoint.vectorTo(fourthPoint)))
                .intersectPlaneLine(thirdPoint, thirdPoint.vectorTo(destination));
        Point3D firstTurn = firstPoint.intersectLines(firstPoint.vectorTo(secondPoint), fourthPoint, fourthPoint.vectorTo(secondTurn));
        return Arrays.asList(
                new Step(routeMarkers.get(0) + 1, 0),
                new Step(routeMarkers.get(1) + 1, firstTurn.distance(secondPoint)),
                new Step(routeMarkers.get(3) + 1, fourthPoint.distance(secondTurn)),
                new Step(routeMarkers.get(2) + 1, destination.distance(thirdPoint))
        );
    }

    private List<Step> getRouteStepsIfPointNotInCircle(Location destination, List<Integer> routeMarkers, List<Point3D> orbitalMarkers) {
        Point3D target = destination.toPoint();
        Sphere planet = destination.getBody().toSphere();
        Point3D firstPoint = orbitalMarkers.get(routeMarkers.get(0));
        Point3D secondPoint = orbitalMarkers.get(routeMarkers.get(1));
        Point3D thirdPoint = orbitalMarkers.get(routeMarkers.get(2));

        Point3D secondTurn = new Plane(firstPoint, firstPoint.vectorTo(secondPoint).crossProduct(firstPoint.vectorTo(thirdPoint))).intersectPlaneLine(planet.getCenter(), planet.getCenter().vectorTo(target));
        Point3D firstTurn = firstPoint.intersectLines(firstPoint.vectorTo(secondPoint), thirdPoint, thirdPoint.vectorTo(secondTurn));

        return Arrays.asList(
                new Step(routeMarkers.get(0) + 1, 0),
                new Step(routeMarkers.get(1) + 1, firstTurn.distance(secondPoint)),
                new Step(routeMarkers.get(3) + 1, secondTurn.distance(thirdPoint)),
                new Step(7, target.distance(destination.getBody().toSphere().getCenter()))
        );
    }

    private int getStartingPoint(Point3D destination, List<Point3D> orbitalMarkers) {
        int closestIndex = 0;
        for (int i = 0; i < orbitalMarkers.size(); i++) {
            if(orbitalMarkers.get(i).distance(destination) < orbitalMarkers.get(closestIndex).distance(destination)) {
                closestIndex = i;
            }
        }
        return closestIndex;
    }

    private int getClosestMarker(Point3D destination, int currentMarker, List<Point3D> orbitalMarkers) {
        int closestIndex = 0;
        Point3D closestPoint = Point3D.MAX;
        double distanceToCurrent = orbitalMarkers.get(currentMarker).distance(destination);
        for (int i = 0; i < orbitalMarkers.size(); i++) {
            double distanceToDestination = orbitalMarkers.get(i).distance(destination);
            if(distanceToDestination > distanceToCurrent && distanceToDestination < closestPoint.distance(destination)) {
                closestIndex = i;
                closestPoint = orbitalMarkers.get(i);
            }
        }
        return closestIndex;
    }

    private List<Point3D> getOrbitalMakers(double radius) {
        return Arrays.asList(
                new Point3D(0, 0, radius),
                new Point3D(0, 0, -radius),
                new Point3D(0, -radius, 0),
                new Point3D(0, radius, 0),
                new Point3D(-radius, 0, 0),
                new Point3D(radius, 0, 0)
        );
    }
}
