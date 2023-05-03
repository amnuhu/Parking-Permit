package org.turntabl.vehicle;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.turntabl.town.Person;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrivateCarTest {


    Person person = new Person("Emma12345", "Emma");
    Person person2 = new Person("Rozz12345", "Rozay");

    List<Person> people = Arrays.asList(person, person2);
    Vehicle vehicle1 =  new PrivateCar(people, "419");


    @Test
    void calculateCharge() {
        double baseCharge = 40.0;
        assertEquals(baseCharge, vehicle1.calculateCharge());
    }

    @Test
    void getOwnersTest() {
        Assertions.assertThat(vehicle1.getOwners()).contains(person);
        Assertions.assertThat(vehicle1.getOwners()).hasSize(2);

    }

}








