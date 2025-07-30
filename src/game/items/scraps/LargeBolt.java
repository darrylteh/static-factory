package game.items.scraps;

import engine.actions.ActionList;
import engine.actors.Actor;
import engine.items.Item;
import engine.positions.GameMap;
import engine.positions.Location;
import game.actions.SellAction;
import game.capabilities.Status;
import game.items.template.Sellable;

/**
 * This class represents a large bolt scrap in the game.
 * Large bolts can be picked up or dropped by the player.
 * Large bolts are represented by '+' on the game map.
 * @author Darryl Teh
 * @version 1.0.0
 */
public class LargeBolt extends Item implements Sellable {
    private static final int SELL_PRICE = 25;

    /**
     * Constructs a new LargeBolt object.
     */
    public LargeBolt() {
        super("Large Bolt", '+', true);
    }

    /**
     * Sells the LargeBolt.
     * The LargeBolt is removed from the actor's inventory, and its selling price
     * is added to the actor's balance.
     *
     * @param actor the actor selling the item
     * @param map the map of actor selling the item
     * @return a String stating the result of the item's sale
     */
    @Override
    public String sellItem(Actor actor, GameMap map) {
        actor.addBalance(SELL_PRICE);
        actor.removeItemFromInventory(this);
        return actor + " sells " + this + " for " + SELL_PRICE + " credits";
    }

    /**
     * List of allowable actions that the LargeBolt allows its owner do to other actor.
     * Allows the owner to sell it to a trader.
     *
     * @param otherActor the other actor (trader)
     * @param location the location of the other actor (trader)
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location){
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.TRADER))
        {
            actions.add(new SellAction(this));
        }
        return actions;
    }
}
