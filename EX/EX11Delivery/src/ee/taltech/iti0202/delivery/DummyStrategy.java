package ee.taltech.iti0202.delivery;

import java.util.List;

public class DummyStrategy implements Strategy {
    private final List<Action> actions;
    private int currentActionIndex;

    /**
     * Constructs a dummy strategy
     * @param actions The actions.
     */
    public DummyStrategy(List<Action> actions) {
        this.actions = actions;
        this.currentActionIndex = 0;
    }

    @Override
    public Action getAction() {
        Action nextAction = actions.get(currentActionIndex);
        currentActionIndex = (currentActionIndex + 1) % actions.size();
        return nextAction;
    }
}
