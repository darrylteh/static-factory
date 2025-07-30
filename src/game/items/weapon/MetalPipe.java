package game.items.weapon;

import engine.actions.ActionList;
import engine.actors.Actor;
import engine.positions.GameMap;
import engine.positions.Location;
import engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.SellAction;
import game.capabilities.Status;
import game.items.template.Sellable;

/**
 * This class represents a metal pipe special scrap in the game.
 * A player with a metal pipe can use it to attack hostile creatures,
 * dealing 1 damage with 20% accuracy.
 * The metal pipe can be picked up or dropped off.
 * @author Darryl Teh
 * @version 1.0.0
 */
public class MetalPipe extends WeaponItem implements Sellable {
    private static final int SELL_PRICE = 35;

    /**
     * Constructs a new MetalPipe object.
     */
    public MetalPipe() {
        super("Metal Pipe", '!', 1, "whacks", 20);
    }

    /**
     * List of allowable actions that the MetalPipe allows its owner do to other actor. Returns a list
     * of Actions with AttackAction to allow the owner to attack an actor (non-trader) with the metal pipe.
     * Also allows the owner to sell it to a trader.
     *
     * @param otherActor the other actor
     * @param location   the location of the other actor
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.TRADER)) {
            actions.add(new SellAction(this));
        }
        else {
            actions.add(new AttackAction(otherActor, location.toString(), this));
        }
        return actions;
    }

    /**
     * Sells the MetalPipe.
     * The MetalPipe is removed from the actor's inventory, and its selling price
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
}
