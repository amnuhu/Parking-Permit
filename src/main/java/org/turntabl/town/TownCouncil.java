package org.turntabl.town;

import org.turntabl.vehicle.Vehicle;
import org.turntabl.vehicle.VehicleType;

import java.util.*;

public class TownCouncil {
    private  int permitNumberCount;

    private Map<VehicleType, Set<Vehicle>> vehiclesWithPermit;

    public TownCouncil() {
        setVehiclesTypes();
    }

    public boolean verifyRequester(Owner owner, Vehicle vehicle) {
        List<Owner> owners = vehicle.getOwners();
        return owners.contains(owner);
    }

    public String generatePermit(Owner requester, Vehicle vehicle) {
        if (verifyRequester(requester, vehicle)){
            if (!carNeedsPermit(vehicle))
                return vehicle.getPermitNumber();

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
        vehiclesWithPermit.get(vehicleType).add(vehicle);
    }

    public boolean carNeedsPermit(Vehicle vehicle) {
        return vehicle.getPermitNumber() == null;
    }

    private   void setVehiclesTypes() {
        this.vehiclesWithPermit = new HashMap<>();
        vehiclesWithPermit.put(VehicleType.PRIVATE_CAR, new HashSet<>());
        vehiclesWithPermit.put(VehicleType.LORRY, new HashSet<>());
        vehiclesWithPermit.put(VehicleType.MOTORBIKE, new HashSet<>());
    }

    public Map<VehicleType, Set<Vehicle>> getVehiclesWithPermit() {
        return vehiclesWithPermit;
    }

    public double getCharge(Vehicle vehicle) {
        return vehicle.calculateCharge();
    }
}
