package org.turntabl.services;

import org.turntabl.town.Person;
import org.turntabl.vehicle.Vehicle;

public interface VerificationService {
    boolean verifyPerson(Person p, Vehicle v);
}
