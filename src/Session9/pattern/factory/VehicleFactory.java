package Session9.pattern.factory;

import Session9.entity.*;
import Session9.engine.Intersection;

import java.util.Random;

public class VehicleFactory {

    private static int counter = 1;

    public static Vehicle create(Intersection intersection) {
        Random rand = new Random();

        if (rand.nextInt(2) == 0) {
            return new StandardVehicle("Car#" + counter++, intersection);
        } else {
            return new PriorityVehicle("Ambulance#" + counter++, intersection);
        }
    }
}
