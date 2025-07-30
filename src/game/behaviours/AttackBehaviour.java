package game.behaviours;

import game.capabilities.Status;
import engine.actions.Action;
import engine.actors.Actor;
import engine.actors.Behaviour;
import engine.positions.Exit;
import engine.positions.GameMap;
import game.actions.AttackAction;

/**
 * This class represents a behaviour where an actor attacks another actor if possible.
 * @author Darryl Teh
 * @version 1.0.0
 */
public class AttackBehaviour implements Behaviour {

    /**
     * Returns an AttackAction to attack, if possible. If no attack is possible, returns null.
     *
     * @param actor the Actor enacting the behaviour
     * @param map   the map that actor is currently on
     * @return an Action, or null if no AttackAction is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            if (exit.getDestination().containsAnActor()) {
                Actor target = exit.getDestination().getActor();
                if (target.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    return new AttackAction(target, exit.getName());
                }
            }
        }
        return null;
    }
}
