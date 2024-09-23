package org.ouchin.models;

import org.ouchin.enums.ProjectStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Project {

    private UUID id;
    private String projectName;
    private Double profitMargin;
    private ProjectStatus status;
    private UUID clientId;
    private List<Component> components;

    public Project() {
        this.components = new ArrayList<>();
    }


    public Project(UUID id, String projectName, double profitMargin, ProjectStatus status, UUID clientId) {
        this.id = id;
        this.projectName = projectName;
        this.profitMargin = profitMargin;
        this.status = status;
        this.clientId = clientId;
        this.components = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Double getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin(Double profitMargin) {
        this.profitMargin = profitMargin;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void addComponent(Component component) {
        this.components.add(component);
    }

    public float calculateCoutTotal() {
        float total = 0;
        for (Component component : components) {
            if (component instanceof Material) {
                Material material = (Material) component;
                total += material.getUnitCost() * material.getQuantity() + material.getTransportCost();
            } else if (component instanceof WorkForce) {
                WorkForce workForce = (WorkForce) component;
                total += workForce.getHourlyRate() * workForce.getWorkHours();
            }
        }
        return total;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", profitMargin=" + profitMargin +
                ", status=" + status +
                ", clientId=" + clientId +
                '}';
    }



}