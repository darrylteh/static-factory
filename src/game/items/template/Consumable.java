package game.items.template;

import engine.actors.Actor;

/**
 * The Consumable abstract class represents a consumable item in the game. A consumable item can be
 * consumed by an actor to gain some effect.
 */
public interface Consumable {

    /**
     * Returns the value of the effect gained by consuming the item.
     *
     * @return the int consume effect value
     */
     int getConsumeEffectValue();

    /**
     * Returns the verb used to describe the consumption action.
     *
     * @return the String consume verb
     */
    String getConsumeVerb();

    /**
     * Consumes the consumable item, applying its effect to the actor. The item is then removed from
     * the actor's inventory.
     *
     * @param actor the actor consuming the item
     * @return the consume verb
     */
     String consumeConsumable(Actor actor);

}
