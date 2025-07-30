package game.items.consumable;

import engine.actions.ActionList;
import engine.actors.Actor;
import engine.items.Item;
import engine.positions.GameMap;
import engine.positions.Location;
import game.actions.ConsumeAction;
import game.actions.SellAction;
import game.capabilities.Status;
import game.items.template.Consumable;
import game.items.template.Sellable;

/**
 * This class represents a large fruit consumable in the game.
 * Large fruits can be picked up or dropped by the player,
 * and consumed to heal the player by 2 hit points.
 * Large fruits are represented by 'O' on the game map.
 * @author Darryl Teh
 * @version 1.0.0
 */
public class LargeFruit extends Item implements Consumable, Sellable {
    private static final int SELL_PRICE = 30;
    private int consumeEffectValue;
    private String consumeVerb;

    /**
     * Constructs a new LargeFruit object.
     */
    public LargeFruit() {
        super("Large Fruit", 'O', true);
        this.consumeEffectValue = 2;
        this.consumeVerb = "heals";
    }

    /**
     * Getter of consumeEffectValue
     * @return consumeEffectValue the value after consuming
     */
    public int getConsumeEffectValue() {
        return consumeEffectValue;
    }

    /**
     * Getter of consumeVerb
     *
     * @return consumeVerb the string that describes the action of consuming
     */
    public String getConsumeVerb() {
        return consumeVerb;
    }

    /**
     * Consumes the consumable item, applying its effect to the actor. The item is then removed from
     * the actor's inventory.
     *
     * @param actor the actor consuming the item
     * @return a special string
     */
    @Override
    public String consumeConsumable(Actor actor) {
        actor.heal(this.getConsumeEffectValue());
        actor.removeItemFromInventory(this);
        String result = actor + " consumed " + this + " and " + this.consumeVerb + " " + actor + " by " + this.getConsumeEffectValue()+" points";
        return result;
    }

    /**
     * Returns a list of allowable actions that the consumable can perform to the current actor. In
     * this case, a ConsumeAction to consume the consumable.
     *
     * @param owner the owner of the consumable
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actions = new ActionList();
        actions.add(new ConsumeAction(this));
        return actions;
    }

    /**
     * Sells the LargeFruit.
     * The LargeFruit is removed from the actor's inventory, and its selling price
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
     * List of allowable actions that the LargeFruit allows its owner do to other actor.
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
