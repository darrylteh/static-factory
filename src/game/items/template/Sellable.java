package game.items.template;

import engine.actors.Actor;
import engine.positions.GameMap;

/**
 * The Sellable interface serves as a way for objects to be sold,
 * if the objects are sold, the seller potentially earns credits.
 *
 * @author Darryl Teh
 * @version 1.0.0
 */
public interface Sellable {
    /**
     * When called, typically sells the item and removes the item from the actor's
     * inventory.
     *
     * @param actor the actor selling the item
     * @param map the map of actor selling the item
     * @return a String stating the result of the item's sale
     */
    String sellItem(Actor actor, GameMap map);
}
