package org.turntabl.vehicle;

import org.turntabl.town.Owner;

import java.util.List;
import java.util.Objects;
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

     @Override
     public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;
          Vehicle vehicle = (Vehicle) o;
          return owners.equals(vehicle.owners) && numberPlate.equals(vehicle.numberPlate) && vehicleType == vehicle.vehicleType;
     }

     @Override
     public int hashCode() {
          return Objects.hash(owners, numberPlate, vehicleType);
     }
}
