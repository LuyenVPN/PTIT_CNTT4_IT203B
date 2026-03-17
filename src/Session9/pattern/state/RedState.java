package Session9.pattern.state;

import Session9.engine.TrafficLight;

public class RedState implements TrafficLightState {

    @Override
    public void handle(TrafficLight context) {
        context.setState(new GreenState());
    }

    @Override
    public String getColor() {
        return "RED";
    }
}
