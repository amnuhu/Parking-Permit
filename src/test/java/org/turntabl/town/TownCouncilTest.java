package org.turntabl.town;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.turntabl.vehicle.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class TownCouncilTest {
    Owner owner = new Owner("Emma12345", "Emma");
    Owner owner2 = new Owner("Rozz12345", "Rozay");
    Owner owner3 = new Owner("12345", "John");
    Owner owner4 = new Owner("12345", "Smith");

    List<Owner> owners = Arrays.asList(owner,owner2);

    List<Owner> oneOwner = Arrays.asList(owner3);
    Vehicle vehicle1 =  new PrivateCar(owners, "419");
    Vehicle vehicle2 =  new Lorry(owners, "419", 2500);
    TownCouncil tc = new TownCouncil();




    @Test
    void verifyRequestorTrue() {
       assertTrue(tc.verifyRequester(owner2,vehicle1));
    }

    @Test
    void verifyRequestorFalse() {
        assertFalse(tc.verifyRequester(owner3,vehicle1));
    }

    @Test
    void issuePermitForRightOwners() {
        String permit = "TC1";
        tc.issuePermit(owner2, vehicle1);
        tc.issuePermit(owner, vehicle1);

        assertEquals(permit,vehicle1.getPermitNumber());
        assertEquals(permit, vehicle1.getPermitNumber());
    }

    @Test
    void issuePermitToNonOwner() {
        String permit = "TC1";
        String permit2 = "TC2";
        tc.issuePermit(owner4, vehicle1);
        assertNotEquals(permit,vehicle1.getPermitNumber() );
        assertNull(vehicle1.getPermitNumber());
    }

    @Test
    void mapOfPermittedVehiclesContainsOnlyThreeKeys() {
        Assertions.assertThat(tc.getVehiclesWithPermit()).containsKeys(VehicleType.LORRY,VehicleType.MOTORBIKE, VehicleType.PRIVATE_CAR);
        Assertions.assertThat(tc.getVehiclesWithPermit()).hasSize(3);
    }

    @Test
    void mapGetsUpdatedWithVehicleAfterIssuing() {
       issuePermitForRightOwners();
       Assertions.assertThat(tc.getVehiclesWithPermit().get(VehicleType.PRIVATE_CAR)).contains(vehicle1);

    }

    @Test
    public void checkAlreadyHasPermitInGeneratePermit() {
        Vehicle vehicle =  new PrivateCar(owners, "419");
        Owner owner = new Owner("Emma12345", "Emma");
        vehicle.setPermitNumber("Yo");
        TownCouncil townCouncil = new TownCouncil();
        TownCouncil townCouncilSpy = spy(townCouncil);
        when(townCouncilSpy.carNeedsPermit(vehicle)).thenReturn(false);
        assertEquals("Yo",townCouncilSpy.generatePermit(owner, vehicle));
    }

    @Test
    public void getVehicleChargeForLorry() {
        Vehicle vehicle2 =  new Lorry(owners, "419", 170);
        double expectedCharge = 35.0;
        double actualCharge = tc.getCharge(vehicle2);
        assertEquals(expectedCharge, actualCharge);
    }

    @Test
    public void getVehicleChargeForPrivateCar() {
        Vehicle vehicle2 =  new PrivateCar(owners, "419");
        double expectedCharge = 40.0;
        double actualCharge = tc.getCharge(vehicle2);
        assertEquals(expectedCharge, actualCharge);
    }

    @Test
    public void getVehicleChargeForMotorbikeScooter() {
        Vehicle vehicle2 =  new Motorbike(owners, "419", 850);
        double expectedCharge = 7.0;
        double actualCharge = tc.getCharge(vehicle2);
        assertEquals(expectedCharge, actualCharge);
    }

    @Test
    public void getVehicleChargeForMotorbikeLargeBike() {
        Vehicle vehicle2 =  new Motorbike(owners, "419", 1000);
        double expectedCharge = 10.0;
        double actualCharge = tc.getCharge(vehicle2);
        assertEquals(expectedCharge, actualCharge);
    }

    @Test
    public void getVehicleChargeForPrivateCarOneOwner() {
        List<Owner> owners = Arrays.asList(owner3,owner3);


        Vehicle vehicle2 =  new PrivateCar(owners, "419");
        double expectedCharge = 20.0;
        double actualCharge = tc.getCharge(vehicle2);
        assertEquals(expectedCharge, actualCharge);
    }



}