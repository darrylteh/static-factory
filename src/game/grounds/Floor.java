package game.grounds;

import engine.actors.Actor;
import engine.positions.Ground;
import game.capabilities.Ability;

/**
 * A class that represents the floor inside a spaceship.
 * Only actors with the ability ENTER_SPACESHIP can move to it.
 * 
 * @author Darryl Teh
 * @version 1.0.0
 */
public class Floor extends Ground {
    /**
     * Constructs a new Floor object.
     */
    public Floor() {
        super('_');
    }

    /**
     * Floor is only passable by actors that have the ability ENTER_SPACESHIP
     *
     * @param actor the Actor to check
     * @return true if actor has capability ENTER_SPACESHIP, false otherwise
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Ability.ENTER_SPACESHIP);
    }
}
