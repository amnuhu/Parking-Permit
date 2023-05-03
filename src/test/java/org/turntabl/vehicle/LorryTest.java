package org.turntabl.vehicle;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.turntabl.exception.InvalidCapacityException;
import org.turntabl.town.Owner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LorryTest {

    Owner owner = new Owner("Emma12345", "Emma");
    Owner owner2 = new Owner("Rozz12345", "Rozay");

    List<Owner> owners = Arrays.asList(owner,owner2);


    @Test
    public void createLorryWithInvalidCapacityThrowsException() {
        assertThrows(InvalidCapacityException.class, ()-> new Lorry(owners,"AS567", -1), "Lorry capacity must be greater than zero" );
        assertThrows(InvalidCapacityException.class, ()-> new Lorry(owners,"AS567", 0), "opoi");
    }

    @Test
    public void createLorryWithValidCapacity() {
        assertDoesNotThrow(()-> new Lorry(owners,"AS567", 1));
        assertDoesNotThrow(()-> new Lorry(owners,"AS567", 1000));
    }

    @Test
    void calculateCharge() throws InvalidCapacityException {
        Vehicle vehicle1 =  new Lorry(owners, "419", 160);
        double expectedCharge = 35;
        assertEquals(expectedCharge, vehicle1.calculateCharge());
    }

    @Test
    void getOwnersTest() throws InvalidCapacityException {
        Vehicle vehicle2=  new Lorry(owners, "419", 200);
        Assertions.assertThat(vehicle2.getOwners()).contains(owner);
        Assertions.assertThat(vehicle2.getOwners()).hasSize(2);

    }
}