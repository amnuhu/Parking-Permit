package org.turntabl.vehicle;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.turntabl.exception.InvalidCapacityException;
import org.turntabl.town.Person;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LorryTest {

    Person person = new Person("Emma12345", "Emma");
    Person person2 = new Person("Rozz12345", "Rozay");

    List<Person> people = Arrays.asList(person, person2);


    @Test
    public void createLorryWithInvalidCapacityThrowsException() {
        assertThrows(InvalidCapacityException.class, ()-> new Lorry(people,"AS567", -1), "Lorry capacity must be greater than zero" );
        assertThrows(InvalidCapacityException.class, ()-> new Lorry(people,"AS567", 0), "opoi");
    }

    @Test
    public void createLorryWithValidCapacity() {
        assertDoesNotThrow(()-> new Lorry(people,"AS567", 1));
        assertDoesNotThrow(()-> new Lorry(people,"AS567", 1000));
    }

    @Test
    void calculateCharge() throws InvalidCapacityException {
        Vehicle vehicle1 =  new Lorry(people, "419", 160);
        double expectedCharge = 35;
        assertEquals(expectedCharge, vehicle1.calculateCharge());
    }

    @Test
    void getOwnersTest() throws InvalidCapacityException {
        Vehicle vehicle2=  new Lorry(people, "419", 200);
        Assertions.assertThat(vehicle2.getOwners()).contains(person);
        Assertions.assertThat(vehicle2.getOwners()).hasSize(2);

    }
}