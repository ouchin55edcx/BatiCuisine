package org.ouchin.models;

import org.ouchin.enums.ComponentType;
import java.util.UUID;

public class Component {
    private UUID id;
    private String name;
    private ComponentType type;
    private double vatRate;
    private UUID projectId;

    public Component(){

    }

    public Component(UUID id, String name, ComponentType type, float vatRate, UUID projectId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.vatRate = vatRate;
        this.projectId = projectId;
    }

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ComponentType getType() {
        return type;
    }

    public void setType(ComponentType type) {
        this.type = type;
    }

    public double getVatRate() {
        return vatRate;
    }

    public void setVatRate(double vatRate) {
        this.vatRate = vatRate;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }
}