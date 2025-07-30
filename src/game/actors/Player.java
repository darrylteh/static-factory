package game.actors;

import game.utility.FancyMessage;
import engine.actions.Action;
import engine.actions.ActionList;
import engine.actors.Actor;
import engine.actors.attributes.BaseActorAttributes;
import engine.displays.Display;
import engine.displays.Menu;
import engine.positions.GameMap;
import engine.weapons.IntrinsicWeapon;
import game.capabilities.Ability;
import game.capabilities.Status;

/**
 * Class representing the Player.
 *
 * @author Darryl Teh
 * @version 1.0.0
 */
public class Player extends Actor {
    /**
     * Constructs a new Player object.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Ability.ENTER_SPACESHIP);
        this.addCapability(Status.FACTORY_EMPLOYEE);
    }

    /**
     * Select and return an action to perform on the current turn.
     * Also displays the Player's name and health.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        display.println(name);
        display.println("HP: " + getAttribute(BaseActorAttributes.HEALTH) + "/" + getAttributeMaximum(BaseActorAttributes.HEALTH));
        display.println("Balance: " + getBalance());
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }

    /**
     * Creates and returns an intrinsic weapon.
     * This method has been overridden to make the Player's punch
     * deal 1 damage with 5% accuracy.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(1, "punches", 5);
    }

    /**
     * Method that can be executed when the actor is unconscious due to the action of another actor
     * Overriden to return YOU_ARE_FIRED FancyMessage.
     *
     * @param actor the perpetrator
     * @param map where the actor fell unconscious
     * @return a string describing what happened when the actor is unconscious
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        map.removeActor(this);
        return FancyMessage.YOU_ARE_FIRED;
    }

    @Override
    public String unconscious(GameMap map) {
        map.removeActor(this);
        return FancyMessage.YOU_ARE_FIRED;
    }
}
