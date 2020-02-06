package com.blackholedevice.scrouteplanner.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "system")
public class System extends VersionedModel {
    @Id
    @GeneratedValue
    private long id;

    private String name;
    private boolean active;
}
