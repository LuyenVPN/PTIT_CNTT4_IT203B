package Session9.pattern.state;

import Session9.engine.TrafficLight;

public class YellowState implements TrafficLightState {

    @Override
    public void handle(TrafficLight context) {
        context.setState(new RedState());
    }

    @Override
    public String getColor() {
        return "YELLOW";
    }
}
