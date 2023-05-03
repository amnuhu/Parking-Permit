package org.turntabl.vehicle;

import org.turntabl.exception.InvalidCapacityException;
import org.turntabl.town.Person;

import java.util.List;

public class Lorry extends Vehicle{
    private final float capacity;
    private final Double extraCharge;

    public Lorry(List<Person> people, String numberPlate, float capacity) throws InvalidCapacityException {
        super(people, numberPlate, 30.0, VehicleType.LORRY);
        if(capacity < 1){
            throw new InvalidCapacityException("Lorry capacity must be greater than zero");
        }
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
        double weightLimit = 150;
        double aboveWeightLimit = 20;
        double remainingWeight = capacity - weightLimit;
        double extraChargeForWeight = Math.ceil(remainingWeight / aboveWeightLimit);

        return  extraChargeForWeight * extraCharge;
    }
}
