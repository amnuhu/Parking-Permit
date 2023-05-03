package org.turntabl.vehicle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



import org.assertj.core.api.Assertions;
import org.turntabl.town.Person;

import java.util.Arrays;
import java.util.List;

class MotorbikeTest {


    Person person = new Person("Emma12345", "Emma");
    Person person2 = new Person("Rozz12345", "Rozay");

    List<Person> people = Arrays.asList(person, person2);
    Vehicle vehicle1 =  new PrivateCar(people, "419");


    @Test
    void calculateChargeForScooter() {
        Vehicle vehicle1 =  new Motorbike(people, "419",850);
        double baseCharge = 7.0;
        assertEquals(baseCharge, vehicle1.calculateCharge());
    }

    @Test
    void calculateChargeForLargeMotorbike() {
        Vehicle vehicle1 =  new Motorbike(people, "419",8500);
        double baseCharge = 10.0;
        assertEquals(baseCharge, vehicle1.calculateCharge());
    }

    @Test
    void getOwnersTest() {
        Assertions.assertThat(vehicle1.getOwners()).contains(person);
        Assertions.assertThat(vehicle1.getOwners()).hasSize(2);

    }

}








