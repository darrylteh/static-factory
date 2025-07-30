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
 * This class represents a metal sheet scrap in the game.
 * Metal sheets can be picked up or dropped by the player.
 * Metal sheets are represented by '%' on the game map.
 * @author Darryl Teh
 * @version 1.0.0
 */
public class MetalSheet extends Item implements Sellable {
    private static final int SELL_PRICE = 20;
    private static final int SPECIAL_SELL_PRICE = 10;
    private static final double SPECIAL_SELL_CHANCE = 0.6;

    /**
     * Constructs a new MetalSheet object.
     */
    public MetalSheet() {
        super("Metal Sheet", '%', true);
    }

    /**
     * Sells the MetalSheet.
     * The MetalSheet is removed from the actor's inventory, and its selling price
     * is added to the actor's balance.
     * There is a 60% chance that the factory will ask for a discount, only paying 10 credits.
     *
     * @param actor the actor selling the item
     * @param map the map of actor selling the item
     * @return a String stating the result of the item's sale
     */
    @Override
    public String sellItem(Actor actor, GameMap map) {
        int sellPrice = SELL_PRICE;
        if (Math.random() < SPECIAL_SELL_CHANCE) {
            sellPrice = SPECIAL_SELL_PRICE;
        }
        actor.addBalance(sellPrice);
        actor.removeItemFromInventory(this);
        return actor + " sells " + this + " for " + sellPrice + " credits";
    }

    /**
     * List of allowable actions that the MetalSheet allows its owner do to other actor.
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
