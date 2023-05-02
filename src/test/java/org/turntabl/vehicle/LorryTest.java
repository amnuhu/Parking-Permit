package org.turntabl.vehicle;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.turntabl.town.Owner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LorryTest {



    Owner owner = new Owner("Emma12345", "Emma");
    Owner owner2 = new Owner("Rozz12345", "Rozay");

    List<Owner> owners = Arrays.asList(owner,owner2);
    Vehicle vehicle1 =  new Lorry(owners, "419", 0);
    Vehicle vehicle2=  new Lorry(owners, "419", 200);


    @Test
    void calculateCharge() {
        double expectedCharge = 42.5;
        assertEquals(expectedCharge, vehicle2.calculateCharge());
    }

    @Test
    void getOwnersTest() {
        Assertions.assertThat(vehicle1.getOwners()).contains(owner);
        Assertions.assertThat(vehicle1.getOwners()).hasSize(2);

    }
}