package org.turntabl.vehicle;

import org.turntabl.town.Owner;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Vehicle {
     private Set<Owner> owners;

     private String numberPlate;


     private Double baseCharge;

     private String permitNumber;

     private VehicleType vehicleType;


     public Vehicle(List<Owner> owners, String numberPlate, Double baseCharge, VehicleType vehicleType) {
          this.owners = owners.stream().collect(Collectors.toSet());
          this.numberPlate = numberPlate;
          this.vehicleType = vehicleType;
          this.baseCharge = baseCharge;
     }

     public Double getBaseCharge() {
          return baseCharge;
     }
     public String getPermitNumber() {
          return permitNumber;
     }

     public void setPermitNumber(String permitNumber) {
          this.permitNumber = permitNumber;
     }

     public void setBaseCharge(Double baseCharge) {
          this.baseCharge = baseCharge;
     }

     public abstract double calculateCharge();

     public List<Owner> getOwners() {
          return owners.stream().toList();
     }
     public VehicleType getVehicleType() {
          return vehicleType;
     }
}
