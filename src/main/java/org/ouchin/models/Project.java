package org.ouchin.models;

import org.ouchin.enums.ProjectStatus;

import java.util.UUID;

public class Project {

    private UUID id ;
    private UUID clientId ;
    private String projectName ;
    private Float profitMargin ;
    private ProjectStatus status ;

    public Project(UUID id, UUID clientId, String projectName, Float profitMargin, ProjectStatus status) {
        this.id = id;
        this.clientId = clientId;
        this.projectName = projectName;
        this.profitMargin = profitMargin;
        this.status = status;
    }

    public Project() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
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

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", projectName='" + projectName + '\'' +
                ", profitMargin=" + profitMargin +
                ", status=" + status +
                '}';
    }




}
