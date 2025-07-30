package game.actors.npcs;

import engine.actions.Action;
import engine.actions.ActionList;
import engine.actions.DoNothingAction;
import engine.actors.Actor;
import engine.displays.Display;
import engine.positions.GameMap;
import game.capabilities.Status;

public class HumanoidFigure extends Actor {

    public HumanoidFigure()
    {
        super("The Entity", 'H', Integer.MAX_VALUE);
        this.addCapability(Status.TRADER);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}
