package org.ouchin.models;

import org.ouchin.enums.ComponentType;
import org.ouchin.presetations.MaterialUi;

import java.util.UUID;

public class Material extends Component {

    private double unitCost;
    private int quantity;
    private double transportCost;
    private double qualityCoefficient;

    public Material() {
        super(UUID.randomUUID(), "", ComponentType.MATERIAL, 0f, null);
    }

    public Material(UUID id, String name, ComponentType type, float vatRate, UUID projectId, double unitCost, int quantity, double transportCost, double qualityCoefficient) {
        super(id, name, type, vatRate, projectId);
        this.unitCost = unitCost;
        this.quantity = quantity;
        this.transportCost = transportCost;
        this.qualityCoefficient = qualityCoefficient;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTransportCost() {
        return transportCost;
    }

    public void setTransportCost(double transportCost) {
        this.transportCost = transportCost;
    }

    public double getQualityCoefficient() {
        return qualityCoefficient;
    }

    public void setQualityCoefficient(double qualityCoefficient) {
        this.qualityCoefficient = qualityCoefficient;
    }



    @Override
    public Double total() {
        return unitCost * quantity * qualityCoefficient + transportCost;
    }
}