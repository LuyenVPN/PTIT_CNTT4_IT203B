package Session9.entity;

import Session9.pattern.observer.TrafficObserver;

public abstract class Vehicle implements Runnable, TrafficObserver {

    protected String id;
    protected int speed;
    protected int priority;
    protected volatile String lightColor = "RED";

    public Vehicle(String id, int speed, int priority) {
        this.id = id;
        this.speed = speed;
        this.priority = priority;
    }

    public abstract void move();

    @Override
    public void update(String lightColor) {
        this.lightColor = lightColor;
    }

    public String getId() {
        return id;
    }
}