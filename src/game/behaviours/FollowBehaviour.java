package game.behaviours;

import engine.actions.Action;
import engine.actions.MoveActorAction;
import engine.actors.Actor;
import engine.actors.Behaviour;
import engine.positions.Exit;
import engine.positions.GameMap;
import engine.positions.Location;
import game.capabilities.Status;

/**
 * A class that figures out a MoveAction that will move the actor one step
 * closer to a target Actor.
 *
 * @author Darryl Teh
 */
public class FollowBehaviour implements Behaviour {

    private Actor target;

    /**
     * Constructor.
     *
     */
    public FollowBehaviour() {
        this.target = null;
    }

    /**
     * Returns a MoveAction to follow target, if possible. If not, returns null.
     * If no target, checks exits of actor for a target.
     * If target found, returns a MoveAction to follow target.
     *
     * @param actor the Actor enacting the behaviour
     * @param map   the map that actor is currently on
     * @return an Action, or null if no AttackAction is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        if (target == null) {
            for (Exit eachExit : here.getExits()) {
                Location destination = eachExit.getDestination();
                if (destination.containsAnActor() && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    target = destination.getActor();
                    break;
                }
            }
        }
        else {
            if (!map.contains(target) || !map.contains(actor))
                return null;

            Location there = map.locationOf(target);

            int currentDistance = distance(here, there);
            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();
                if (destination.canActorEnter(actor)) {
                    int newDistance = distance(destination, there);
                    if (newDistance < currentDistance) {
                        return new MoveActorAction(destination, exit.getName());
                    }
                }
            }
        }

        return null;
    }

    /**
     * Compute the Manhattan distance between two locations.
     *
     * @param a the first location
     * @param b the first location
     * @return the number of steps between a and b if you only move in the four cardinal directions.
     */
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}
