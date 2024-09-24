package org.ouchin.models;

import java.time.LocalDate;
import java.util.UUID;

public class Estimate {

    private UUID id;
    private float estimatedAmount;
    private LocalDate issueDate;
    private LocalDate validityDate;
    private boolean isAccepted;
    private UUID projectId;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public float getEstimatedAmount() {
        return estimatedAmount;
    }

    public void setEstimatedAmount(float estimatedAmount) {
        this.estimatedAmount = estimatedAmount;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(LocalDate validityDate) {
        this.validityDate = validityDate;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }


    public Estimate(UUID id, float estimatedAmount, LocalDate issueDate, LocalDate validityDate, boolean isAccepted, UUID projectId) {
        this.id = id;
        this.estimatedAmount = estimatedAmount;
        this.issueDate = issueDate;
        this.validityDate = validityDate;
        this.isAccepted = isAccepted;
        this.projectId = projectId;
    }




}
