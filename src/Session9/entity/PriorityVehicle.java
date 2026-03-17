package Session9.entity;

import Session9.engine.Intersection;
import Session9.util.Logger;

public class PriorityVehicle extends Vehicle {

    private Intersection intersection;

    public PriorityVehicle(String id, Intersection intersection) {
        super(id, 2, 10);
        this.intersection = intersection;
    }

    @Override
    public void move() {
        try {
            Logger.log(id + " là xe ưu tiên → vượt đèn đỏ");
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
