package ee.taltech.iti0202.delivery;

import java.util.ArrayList;
import java.util.List;

public class DummyStrategy implements Strategy {
    private List<Action> actions = new ArrayList<Action>();

    public DummyStrategy(List<Action> actions) {
    }

    @Override
    public Action getAction() {
        return actions.removeFirst();
    }
}
