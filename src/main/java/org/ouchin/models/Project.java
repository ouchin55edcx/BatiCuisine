package org.ouchin.models;

import org.ouchin.enums.ProjectStatus;

import java.util.UUID;

public class Project {

    private UUID id;
    private String projectName;
    private Float profitMargin;
    private ProjectStatus status;
    private UUID clientId;


    public Project() {
    }

    public Project(UUID id, String projectName, Float profitMargin, ProjectStatus status, UUID clientId) {
        this.id = id;
        this.projectName = projectName;
        this.profitMargin = profitMargin;
        this.status = status;
        this.clientId = clientId;
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

    public Float getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin(Float profitMargin) {
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