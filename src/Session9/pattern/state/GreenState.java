package Session9.pattern.state;

import Session9.engine.TrafficLight;

public class GreenState implements TrafficLightState {

    @Override
    public void handle(TrafficLight context) {
        context.setState(new YellowState());
    }

    @Override
    public String getColor() {
        return "GREEN";
    }
}