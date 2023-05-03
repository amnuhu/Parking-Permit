package org.turntabl.town;

import org.turntabl.services.PermitIssuerService;
import org.turntabl.services.VerificationService;
import org.turntabl.vehicle.Vehicle;
import org.turntabl.vehicle.VehicleType;

import java.util.*;

public class TownCouncil {
    private  int permitNumberCount;

    private final VerificationService verificationService;

    private final PermitIssuerService permitIssuerService;

    private Map<VehicleType, Set<Vehicle>> vehiclesWithPermit;

    public TownCouncil(VerificationService verificationService, PermitIssuerService permitIssuerService) {
        this.verificationService = verificationService;
        this.permitIssuerService = permitIssuerService;
        setVehiclesTypes();
    }


    public String issuePermit(Person requester, Vehicle vehicle) {
        String permit;
        if (verificationService.verifyPerson(requester, vehicle)) {
            if (vehicle.getVehicleType() == VehicleType.LORRY) {
                permit = generateLorryPermit(requester,vehicle);
            }
            else
                permit = permitIssuerService.issuePermit(vehicle);

            vehicle.setPermitNumber(permit);
            addPermittedVehicles(vehicle);
            return permit;
        }
        return null;

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

    private String generateLorryPermit(Person requester, Vehicle vehicle) {
        if (!carNeedsPermit(vehicle))
            return vehicle.getPermitNumber();

        String number = "TC" +(permitNumberCount + 1);
        this.permitNumberCount++;

        return number;
    }

}


//    public String generatePermit(Person requester, Vehicle vehicle) {
//        if (verifyRequester(requester, vehicle)){
//            if (!carNeedsPermit(vehicle))
//                return vehicle.getPermitNumber();
//
//            String number = "TC" +(permitNumberCount + 1);
//            this.permitNumberCount++;
//
//            addPermittedVehicles(vehicle);;
//            return number;
//        }
//        return null;
//    }

//    public boolean verifyRequester(Person person, Vehicle vehicle) {
//        List<Person> people = vehicle.getOwners();
//        return people.contains(person);
//    }

//    public void issuePermit(Person requester, Vehicle vehicle) {
//        String permit = generatePermit(requester, vehicle);
//        vehicle.setPermitNumber(permit);
//    }
