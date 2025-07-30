package game.items.consumable;

import engine.actions.ActionList;
import engine.actors.Actor;
import engine.items.Item;
import game.actions.ConsumeAction;
import game.items.template.Consumable;

/**
 * This class represents a small fruit consumable in the game.
 * Small fruits can be picked up or dropped by the player,
 * and consumed to heal the player by 1 hit point.
 * Small fruits are represented by 'o' on the game map.
 * @author Darryl Teh
 * @version 1.0.0
 */
public class SmallFruit extends Item implements Consumable {
    private int consumeEffectValue;
    private String consumeVerb;

    /**
     * Constructs a new SmallFruit object.
     */
    public SmallFruit() {
        super("Small Fruit", 'o', true);
        this.consumeEffectValue = 1;
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
}
