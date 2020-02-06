package com.blackholedevice.scrouteplanner.entity;

import lombok.Data;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class VersionedModel {

    @ManyToOne
    private GameVersion version;
}
