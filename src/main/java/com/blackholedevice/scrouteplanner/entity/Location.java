package com.blackholedevice.scrouteplanner.entity;

import com.blackholedevice.scrouteplanner.model.Point3D;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "location")
public class Location extends VersionedModel {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Body body;

    private String name;
    private double x;
    private double y;
    private double z;
    private boolean active;

    public Point3D toPoint() {
        return new Point3D(this.x, this.y, this.z);
    }
}
