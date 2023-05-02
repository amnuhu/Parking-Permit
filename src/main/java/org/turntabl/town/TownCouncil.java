package org.turntabl.town;

import org.turntabl.vehicle.Vehicle;
import org.turntabl.vehicle.VehicleType;

import java.util.*;

public class TownCouncil {

    private  int permitNumberCount;
    private List<Vehicle> vehicles;
    private List<Owner> registeredUsers;

    private Map<VehicleType, List<Vehicle>> vehiclesWithPermit;

    public TownCouncil() {
        this.vehicles = new ArrayList<>();
        this.registeredUsers = new ArrayList<>();
        setVehiclesTypes();
    }

    public boolean verifyRequester(Owner owner, Vehicle vehicle) {
        List<Owner> owners = vehicle.getOwners();
        return owners.contains(owner);
    }

    public String generatePermit(Owner requester, Vehicle vehicle) {
        if (!carNeedsPermit(vehicle))
            return vehicle.getPermitNumber();

        if (verifyRequester(requester, vehicle)){
            String number = "TC" +(permitNumberCount + 1);
            this.permitNumberCount++;

            addPermittedVehicles(vehicle);;
            return number;
        }

        return null;
    }

    public void issuePermit(Owner requester, Vehicle vehicle) {
        String permit = generatePermit(requester, vehicle);
        vehicle.setPermitNumber(permit);
    }

    public void addPermittedVehicles(Vehicle vehicle) {
        VehicleType vehicleType = vehicle.getVehicleType();
        switch (vehicleType) {
            case LORRY : {
                List<Vehicle> lorry = vehiclesWithPermit.get(VehicleType.LORRY);
                lorry.add(vehicle);
            }

            case MOTORBIKE: {
                List<Vehicle> motorbike = vehiclesWithPermit.get(VehicleType.MOTORBIKE);
                motorbike.add(vehicle);
            }
            case PRIVATE_CAR: {
                List<Vehicle> privateCar = vehiclesWithPermit.get(VehicleType.PRIVATE_CAR);
                privateCar.add(vehicle);
            }
        }
    }

    public boolean carNeedsPermit(Vehicle vehicle) {
        return vehicle.getPermitNumber() == null;
    }

    private   void setVehiclesTypes() {
        this.vehiclesWithPermit = new HashMap<>();
        vehiclesWithPermit.put(VehicleType.PRIVATE_CAR, new ArrayList<>());
        vehiclesWithPermit.put(VehicleType.LORRY, new ArrayList<>());
        vehiclesWithPermit.put(VehicleType.MOTORBIKE, new ArrayList<>());
    }

    public Map<VehicleType, List<Vehicle>> getVehiclesWithPermit() {
        return vehiclesWithPermit;
    }

    public double getCharge(Vehicle vehicle) {
        return vehicle.calculateCharge();
    }
}
