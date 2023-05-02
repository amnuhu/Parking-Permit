package org.turntabl.vehicle;

import org.turntabl.town.Owner;

import java.util.List;

public class PrivateCar extends Vehicle {


    public PrivateCar(List<Owner> owners, String numberPlate) {
        super(owners, numberPlate, 20.0,VehicleType.PRIVATE_CAR);
    }

    @Override
    public double calculateCharge() {

        return getBaseCharge() * getOwners().size();
    }
}
