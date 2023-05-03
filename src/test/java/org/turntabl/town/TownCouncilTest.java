//package org.turntabl.town;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.turntabl.exception.InvalidCapacityException;
//import org.turntabl.vehicle.*;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.spy;
//
//class TownCouncilTest {
//    Person person = new Person("Emma12345", "Emma");
//    Person person2 = new Person("Rozz12345", "Rozay");
//    Person person3 = new Person("12345", "John");
//    Person person4 = new Person("12345", "Smith");
//
//    List<Person> people = Arrays.asList(person, person2);
//
//    List<Person> onePerson = Arrays.asList(person3);
//    Vehicle vehicle1 =  new PrivateCar(people, "419");
//    TownCouncil tc = new TownCouncil();
//
//
//    @Test
//    void verifyRequestorTrue() {
//       assertTrue(tc.verifyRequester(person2,vehicle1));
//    }
//
//    @Test
//    void verifyRequestorFalse() {
//        assertFalse(tc.verifyRequester(person3,vehicle1));
//    }
//
//    @Test
//    void issuePermitForRightOwners() {
//        String permit = "TC1";
//        tc.issuePermit(person2, vehicle1);
//        tc.issuePermit(person, vehicle1);
//
//        assertEquals(permit,vehicle1.getPermitNumber());
//        assertEquals(permit, vehicle1.getPermitNumber());
//    }
//
//    @Test
//    void issuePermitToNonOwner() {
//        String permit = "TC1";
//        String permit2 = "TC2";
//        tc.issuePermit(person4, vehicle1);
//        assertNotEquals(permit,vehicle1.getPermitNumber() );
//        assertNull(vehicle1.getPermitNumber());
//    }
//
//    @Test
//    void mapOfPermittedVehiclesContainsOnlyThreeKeys() {
//        Assertions.assertThat(tc.getVehiclesWithPermit()).containsKeys(VehicleType.LORRY,VehicleType.MOTORBIKE, VehicleType.PRIVATE_CAR);
//        Assertions.assertThat(tc.getVehiclesWithPermit()).hasSize(3);
//    }
//
//    @Test
//    void mapGetsUpdatedWithVehicleAfterIssuing() {
//       issuePermitForRightOwners();
//       Assertions.assertThat(tc.getVehiclesWithPermit().get(VehicleType.PRIVATE_CAR)).contains(vehicle1);
//
//    }
//
////    @Test
////    public void checkAlreadyHasPermitInGeneratePermit() {
////        Vehicle vehicle =  new PrivateCar(owners, "419");
////        Owner owner = new Owner("Emma12345", "Emma");
////        vehicle.setPermitNumber("Yo");
////        TownCouncil townCouncil = new TownCouncil();
////        TownCouncil townCouncilSpy = spy(townCouncil);
////        when(townCouncilSpy.carNeedsPermit(vehicle)).thenReturn(false);
////        assertEquals("Yo",townCouncilSpy.generatePermit(owner, vehicle));
////    }
//@Test
//public void checkAlreadyHasPermitInGeneratePermit() {
//    tc.issuePermit(person,vehicle1);
//    assertEquals("TC1", tc.generatePermit(person2,vehicle1));
//}
//
//    @Test
//    public void getVehicleChargeForLorry() throws InvalidCapacityException {
//        Vehicle vehicle2 =  new Lorry(people, "419", 170);
//        double expectedCharge = 35.0;
//        double actualCharge = tc.getCharge(vehicle2);
//        assertEquals(expectedCharge, actualCharge);
//    }
//
//    @Test
//    public void getVehicleChargeForPrivateCar() {
//        Vehicle vehicle2 =  new PrivateCar(people, "419");
//        double expectedCharge = 40.0;
//        double actualCharge = tc.getCharge(vehicle2);
//        assertEquals(expectedCharge, actualCharge);
//    }
//
//    @Test
//    public void getVehicleChargeForMotorbikeScooter() {
//        Vehicle vehicle2 =  new Motorbike(people, "419", 850);
//        double expectedCharge = 7.0;
//        double actualCharge = tc.getCharge(vehicle2);
//        assertEquals(expectedCharge, actualCharge);
//    }
//
//    @Test
//    public void getVehicleChargeForMotorbikeLargeBike() {
//        Vehicle vehicle2 =  new Motorbike(people, "419", 1000);
//        double expectedCharge = 10.0;
//        double actualCharge = tc.getCharge(vehicle2);
//        assertEquals(expectedCharge, actualCharge);
//    }
//
//    @Test
//    public void getVehicleChargeForPrivateCarOneOwner() {
//        List<Person> people = Arrays.asList(person3, person3);
//
//
//        Vehicle vehicle2 =  new PrivateCar(people, "419");
//        double expectedCharge = 20.0;
//        double actualCharge = tc.getCharge(vehicle2);
//        assertEquals(expectedCharge, actualCharge);
//    }
//
//
//
//}