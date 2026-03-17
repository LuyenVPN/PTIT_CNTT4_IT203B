package Session9.entity;

import Session9.engine.Intersection;
import Session9.util.Logger;
public class StandardVehicle extends Vehicle {

    private Intersection intersection;

    public StandardVehicle(String id, Intersection intersection) {
        super(id, 1, 1);
        this.intersection = intersection;
    }

    @Override
    public void move() {
        try {
            if (lightColor.equals("RED")) {
                Logger.log(id + " đang dừng vì đèn đỏ");
                Thread.sleep(1000);
            }
            intersection.enter(this);
        } catch (Exception e) {
            Logger.log(id + " lỗi: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        move();
    }
}
