package Session9.pattern.state;

import Session9.engine.TrafficLight;

public interface TrafficLightState {
    void handle(TrafficLight context);
    String getColor();
}
