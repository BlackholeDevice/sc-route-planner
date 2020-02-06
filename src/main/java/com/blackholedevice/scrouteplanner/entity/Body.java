package com.blackholedevice.scrouteplanner.entity;

import com.blackholedevice.scrouteplanner.model.Point3D;
import com.blackholedevice.scrouteplanner.model.Sphere;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "body")
public class Body extends VersionedModel {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Body parent;

    @ManyToOne
    private System system;

    @Enumerated(EnumType.STRING)
    private BodyType type;
    private String name;
    private double radius;

    @Column(name = "om_radius")
    private double omRadius;
    private boolean active;

    public Sphere toSphere() {
        return new Sphere(Point3D.ORIGIN, radius);
    }

    public enum BodyType {
        STAR,
        PLANET,
        MOON
    }
}
