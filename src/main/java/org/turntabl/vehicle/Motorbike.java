package org.turntabl.vehicle;

import org.turntabl.town.Person;

import java.util.List;

public class Motorbike extends Vehicle{
    private float engineCapacity;

    public Motorbike(List<Person> people, String numberPlate, float engineCapacity) {
        super(people, numberPlate, 0.0, VehicleType.MOTORBIKE);
        if (engineCapacity <= 850) {
            super.setBaseCharge(7.0);
        }
        else super.setBaseCharge(10.0);
    }


    @Override
    public double calculateCharge() {
        return getBaseCharge();
    }
}
