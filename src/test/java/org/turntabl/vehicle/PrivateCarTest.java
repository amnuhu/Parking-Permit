package org.turntabl.vehicle;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.turntabl.town.Owner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrivateCarTest {


    Owner owner = new Owner("Emma12345", "Emma");
    Owner owner2 = new Owner("Rozz12345", "Rozay");

    List<Owner> owners = Arrays.asList(owner,owner2);
    Vehicle vehicle1 =  new PrivateCar(owners, "419");


    @Test
    void calculateCharge() {
        double baseCharge = 40.0;
        assertEquals(baseCharge, vehicle1.calculateCharge());
    }

    @Test
    void getOwnersTest() {
        Assertions.assertThat(vehicle1.getOwners()).contains(owner);
        Assertions.assertThat(vehicle1.getOwners()).hasSize(2);

    }

}








