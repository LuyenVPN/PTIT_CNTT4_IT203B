package Session9.engine;

import Session9.pattern.observer.TrafficObserver;
import Session9.pattern.state.*;

import java.util.ArrayList;
import java.util.List;

public class TrafficLight implements Runnable {

    private TrafficLightState state;
    private List<TrafficObserver> observers = new ArrayList<>();

    public TrafficLight() {
        state = new RedState();
    }

    public void setState(TrafficLightState state) {
        this.state = state;
        notifyObservers();
    }

    public void register(TrafficObserver o) {
        observers.add(o);
    }

    public void notifyObservers() {
        for (TrafficObserver o : observers) {
            o.update(state.getColor());
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(2000);
                state.handle(this);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
