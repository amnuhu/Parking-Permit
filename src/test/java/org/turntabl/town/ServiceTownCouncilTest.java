package org.turntabl.town;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.turntabl.exception.InvalidCapacityException;
import org.turntabl.services.PermitIssuerService;
import org.turntabl.services.VerificationService;
import org.turntabl.vehicle.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



public class ServiceTownCouncilTest {
    Person person1 = new Person("Emma12345", "Emma");
    Person person2 = new Person("Rozz12345", "Rozay");
    Person person3 = new Person("12345", "John");
    Person person4 = new Person("12345", "Smith");

    List<Person> people = Arrays.asList(person1, person2);

    List<Person> onePerson = Arrays.asList(person3);
    Vehicle privateCar =  new PrivateCar(people, "419");
    Vehicle lorry =  new Lorry(people, "419", 160);

    Vehicle motorbike =  new Motorbike(people, "419",850);

    VerificationService verificationServiceMock = mock(VerificationService.class);
    PermitIssuerService permitIssuerServiceMock = mock(PermitIssuerService.class);
    TownCouncil townCouncil = new TownCouncil(verificationServiceMock, permitIssuerServiceMock);



    public ServiceTownCouncilTest() throws InvalidCapacityException {
    }


    @Test
    public void verifyRequesterTestPrivateCar(){
        when(verificationServiceMock.verifyPerson(person1, privateCar)).thenReturn(true);
        when(permitIssuerServiceMock.issuePermit(privateCar)).thenReturn("TC1");
        String expectedPermitNumber = "TC1";
        String actualPermitNumber = townCouncil.issuePermit(person1,privateCar);
        Assertions.assertThat(actualPermitNumber).isEqualTo(expectedPermitNumber);
    }

    @Test
    public void verifyRequesterTestMotorBike(){
        when(verificationServiceMock.verifyPerson(person1, motorbike)).thenReturn(true);
        when(permitIssuerServiceMock.issuePermit(motorbike)).thenReturn("TC1");
        String expectedPermitNumber = "TC1";
        String actualPermitNumber = townCouncil.issuePermit(person1,motorbike);
        Assertions.assertThat(actualPermitNumber).isEqualTo(expectedPermitNumber);
    }
    @Test
    public void verifyInvalidRequesterTestMotorBike(){
        when(verificationServiceMock.verifyPerson(person2, motorbike)).thenReturn(false);
        when(permitIssuerServiceMock.issuePermit(motorbike)).thenReturn("TC1");
        String expectedPermitNumber = "TC1";
        String actualPermitNumber = townCouncil.issuePermit(person2,motorbike);
        assertNull(actualPermitNumber);
        verify(permitIssuerServiceMock, times(0)).issuePermit(motorbike);
    }

    @Test
    public void verifyRequesterTestLorry(){
        when(verificationServiceMock.verifyPerson(person1, lorry)).thenReturn(true);
        String expectedPermitNumber = "TC1";
        String actualPermitNumber = townCouncil.issuePermit(person1,lorry);
        Assertions.assertThat(actualPermitNumber).isEqualTo(expectedPermitNumber);
        verify(permitIssuerServiceMock, times(0)).issuePermit(lorry);
    }

    @Test
    void mapOfPermittedVehiclesContainsOnlyThreeKeys() {
        Assertions.assertThat(townCouncil.getVehiclesWithPermit()).containsKeys(VehicleType.LORRY,VehicleType.MOTORBIKE, VehicleType.PRIVATE_CAR);
        Assertions.assertThat(townCouncil.getVehiclesWithPermit()).hasSize(3);
    }
    @Test
    void mapGetsUpdatedWithVehiclesAfterIssuing() {
        verifyRequesterTestMotorBike();
        verifyRequesterTestPrivateCar();
        verifyRequesterTestLorry();
        Assertions.assertThat(townCouncil.getVehiclesWithPermit().get(VehicleType.PRIVATE_CAR)).contains(privateCar);
        Assertions.assertThat(townCouncil.getVehiclesWithPermit().get(VehicleType.LORRY)).contains(lorry);
        Assertions.assertThat(townCouncil.getVehiclesWithPermit().get(VehicleType.MOTORBIKE)).contains(motorbike);

    }

    @Test
    void checkAlreadyHasPermitInGeneratePermit() {
        when(verificationServiceMock.verifyPerson(any(Person.class), any(Lorry.class))).thenReturn(true);
        when(permitIssuerServiceMock.issuePermit(motorbike)).thenReturn("TC1");
        townCouncil.issuePermit(person1,lorry);
        assertEquals("TC1", townCouncil.issuePermit(person2,lorry));
}

    @Test
    public void getVehicleChargeForLorry() throws InvalidCapacityException {
        double expectedCharge = 35.0;
        double actualCharge = townCouncil.getCharge(lorry);
        assertEquals(expectedCharge, actualCharge);
    }
    @Test
    public void getVehicleChargeForPrivateCar() {
        double expectedCharge = 40.0;
        double actualCharge = townCouncil.getCharge(privateCar);
        assertEquals(expectedCharge, actualCharge);
    }

    @Test
    public void getVehicleChargeForMotorbikeScooter() {
        double expectedCharge = 7.0;
        double actualCharge = townCouncil.getCharge(motorbike);
        assertEquals(expectedCharge, actualCharge);
    }

    @Test
    public void getVehicleChargeForMotorbikeLargeBike() {
        Vehicle vehicle2 =  new Motorbike(people, "419", 1000);
        double expectedCharge = 10.0;
        double actualCharge = townCouncil.getCharge(vehicle2);
        assertEquals(expectedCharge, actualCharge);
    }

}
