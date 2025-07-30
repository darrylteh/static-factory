package game.actions;

import engine.actions.Action;
import engine.actors.Actor;
import engine.positions.GameMap;
import game.items.template.Consumable;

/**
 * This class represents the action of an actor
 * consuming a consumable.
 * @author Darryl Teh
 * @version 1.0.0
 */
public class ConsumeAction extends Action
{
    private Consumable consumable;

    /**
     * Constructs a new ConsumeAction object.
     *
     * @param consumable the consumable to be consumed
     */
    public ConsumeAction(Consumable consumable) {
        this.consumable = consumable;
    }

    /**
     * Perform the consume action.
     *
     * @param actor The actor consuming the consumable
     * @param map The map the actor is on
     * @return a description of what happened (the result of the consumable being consumed) that can be displayed to the user
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = consumable.consumeConsumable(actor); 
        return result;
    }

    /**
     * Describe what action will be performed if ConsumeAction is chosen in the menu.
     *
     * @param actor The actor consuming the consumable
     * @return the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + consumable;
    }
}
