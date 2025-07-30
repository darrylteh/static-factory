package game.actions;

import engine.actions.Action;
import engine.actors.Actor;
import engine.positions.GameMap;
import game.items.template.Sellable;

/**
 * This class represents an actor selling an object.
 *
 * @author Darryl Teh
 * @version 1.0.0
 */
public class SellAction extends Action {
    private Sellable itemSold;

    /**
     * Constructs a new SellAction object.
     *
     * @param itemSold the item to be sold
     */
    public SellAction(Sellable itemSold) {
        this.itemSold = itemSold;
    }

    /**
     * Perform the sell action.
     *
     * @param actor The actor selling the item
     * @param map The map the actor is on
     * @return a description of what happened (the result of the item being sold) that can be displayed to the user
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = itemSold.sellItem(actor, map);
        return result;
    }

    /**
     * Describe what action will be performed if SellAction is chosen in the menu.
     *
     * @param actor The actor selling the item
     * @return the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " sells " + itemSold + ".";
    }
}
