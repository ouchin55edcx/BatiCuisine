package org.ouchin.models;

import org.ouchin.enums.ComponentType;
import java.util.UUID;

public class WorkForce extends Component {
    private double hourlyRate;
    private double workHours;
    private double workerProductivity;


    public WorkForce() {
        super(UUID.randomUUID(), "", ComponentType.LABOR, 0.0f, null);

    }

    public WorkForce(UUID id, String name, ComponentType type, float vatRate, UUID projectId, double hourlyRate, double workHours, double workerProductivity) {
        super(id, name, type, vatRate, projectId);
        this.hourlyRate = hourlyRate;
        this.workHours = workHours;
        this.workerProductivity = workerProductivity;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getWorkHours() {
        return workHours;
    }

    public void setWorkHours(double workHours) {
        this.workHours = workHours;
    }

    public double getWorkerProductivity() {
        return workerProductivity;
    }

    public void setWorkerProductivity(double workerProductivity) {
        this.workerProductivity = workerProductivity;
    }



//todo : override calculate total


    @Override
    public Double total() {
        return hourlyRate * workHours * workerProductivity;
    }
}