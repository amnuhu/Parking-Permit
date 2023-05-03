package org.turntabl.vehicle;

import org.turntabl.town.Person;

import java.util.List;

public class PrivateCar extends Vehicle {


    public PrivateCar(List<Person> people, String numberPlate) {
        super(people, numberPlate, 20.0,VehicleType.PRIVATE_CAR);
    }

    @Override
    public double calculateCharge() {

        return getBaseCharge() * getOwners().size();
    }
}
