package com.project.paradoxplatformer.utils.endgame.condition;

import com.project.paradoxplatformer.utils.endgame.VictoryCondition;

/**
 * A composite victory condition that combines two conditions using a logical AND.
 */
public class CompositeVictoryCondition implements VictoryCondition {

    private final VictoryCondition condition1;
    private final VictoryCondition condition2;

    /**
     * Constructs a CompositeVictoryCondition.
     *
     * @param condition1 the first condition
     * @param condition2 the second condition
     */
    public CompositeVictoryCondition(final VictoryCondition condition1, final VictoryCondition condition2) {
        this.condition1 = condition1;
        this.condition2 = condition2;
    }

    /**
     * Checks if both conditions have been met.
     *
     * @return true if both conditions are met, false otherwise.
     */
    @Override
    public boolean win() {
        return condition1.win() && condition2.win();
    }
}