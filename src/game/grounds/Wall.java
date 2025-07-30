package game.grounds;

import engine.actors.Actor;
import engine.positions.Ground;

/**
 * A class that represents an impassable wall.
 * 
 * Modified by:
 * @author Darryl Teh
 * @version 1.0.0
 */
public class Wall extends Ground {

    /**
     * Constructs a new Wall object.
     */
    public Wall() {
        super('#');
    }

    /**
     * This method has been overridden to return false,
     * as walls are impassable.
     *
     * @param actor the Actor to check
     * @return false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
