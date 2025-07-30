package game.actions;

import engine.actions.Action;
import engine.actors.Actor;
import engine.positions.GameMap;
import game.items.template.Monologuable;

/**
 * This class represents an actor monologuing.
 *
 * @author Darryl Teh
 * @author Tan Zhong Yi
 * @version 1.0.0
 */
public class MonologueAction extends Action {
    private Monologuable monologist;

    /**
     * Constructs a new MonologueAction object.
     *
     * @param monologist the monologist that monologues
     */
    public MonologueAction(Monologuable monologist)
    {
        this.monologist = monologist;
    }

    /**
     * Perform the monologue action.
     *
     * @param actor The actor listening to the monologue
     * @param map The map the actor is on
     * @return a description of what happened (the result of the monologue) that can be displayed to the user
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = monologist + ": \"" + monologist.generateMonologue(actor) + "\"";
        return result;
    }

    /**
     * Describe what action will be performed if MonologueAction is chosen in the menu.
     *
     * @param actor The actor listening to the monologue
     * @return the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " listens to " + monologist;
    }
}
