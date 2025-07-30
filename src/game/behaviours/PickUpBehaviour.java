package game.behaviours;

import game.utility.RandomUtil;

import java.util.List;

import engine.actions.Action;
import engine.actors.Actor;
import engine.actors.Behaviour;
import engine.items.Item;
import engine.items.PickUpAction;
import engine.positions.GameMap;
import engine.positions.Location;

/**
 * This class represents a behaviour where an actor picks up scraps at their location.
 * @author Darryl Teh
 * @version 1.0.0
 */
public class PickUpBehaviour implements Behaviour {
    /**
     * Returns an PickUpAction to pick up a random item, if possible.
     * If no item to pick up, returns null.
     *
     * @param actor the Actor enacting the behaviour
     * @param map   the map that actor is currently on
     * @return an Action, or null if no AttackAction is possible
     */
    public Action getAction(Actor actor, GameMap map) {
        Location actorLocation = map.locationOf(actor);
        List<Item> items = actorLocation.getItems();
        if (!items.isEmpty()) {
            Item randomItem = items.get(RandomUtil.generateRandomInt(0, items.size()));
            return new PickUpAction(randomItem);
        }
        else {
            return null;
        }
    }
}
