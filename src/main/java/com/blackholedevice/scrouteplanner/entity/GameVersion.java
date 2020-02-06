package com.blackholedevice.scrouteplanner.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "game_version")
public class GameVersion {
    @Id
    @GeneratedValue
    private long id;
    private String description;
    @Enumerated(EnumType.STRING)
    private ReleaseType type;
    private boolean active;

    public enum ReleaseType {
        LIVE,
        PTU
    }
}
