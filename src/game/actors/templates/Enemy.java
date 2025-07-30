package game.actors.templates;

import game.actions.AttackAction;
import game.capabilities.Status;

import java.util.Map;
import java.util.TreeMap;

import engine.actions.Action;
import engine.actions.ActionList;
import engine.actions.DoNothingAction;
import engine.actors.Actor;
import engine.actors.Behaviour;
import engine.displays.Display;
import engine.positions.GameMap;

/**
 * This abstract class represents an enemy actor in the game.
 * An enemy actor can be attacked by actors with the HOSTILE_TO_ENEMY status.
 * @author Darryl Teh
 * @version 1.0.0
 */
public abstract class Enemy extends Actor {
    protected Map<Integer, Behaviour> behaviours = new TreeMap<>();

    public Enemy(String name, char displayChar, int hitPoints)
    {
        super(name, displayChar, hitPoints);
    }

    /**
     * At each turn, select a valid action for enemy to perform.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in
     *                   conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is
     * found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                return action;
            }
        }
        return new DoNothingAction();
    }

    /**
     * The enemy can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }
}
