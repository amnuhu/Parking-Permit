package org.turntabl.vehicle;

import org.turntabl.town.Owner;

import java.util.List;

public class Lorry extends Vehicle{
    private final double weightLimit = 150;
    private final double aboveWeightLimit = 20;
    private float capacity;
    private Double extraCharge;

    public Lorry(List<Owner> owners, String numberPlate, float capacity) {
        super(owners, numberPlate, 30.0, VehicleType.LORRY);
        this.capacity = capacity;
        this.extraCharge = 5.0;
    }

    @Override
    public double calculateCharge() {
        if (capacity <= 150) {
            return getBaseCharge();
        }
        return calculateExtraCharge() + getBaseCharge();
    }

    private double calculateExtraCharge() {
        double remainingWeight = capacity % weightLimit;

        double extraChargeForWeight = remainingWeight / aboveWeightLimit;

        return  extraChargeForWeight * extraCharge;
    }
}
