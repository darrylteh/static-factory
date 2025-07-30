package game.actors.npcs;

import game.actors.templates.Enemy;
import game.behaviours.FollowBehaviour;
import game.behaviours.PickUpBehaviour;
import game.behaviours.WanderBehaviour;
import game.capabilities.Ability;
import game.utility.RandomUtil;

import java.util.List;

import engine.actors.Actor;
import engine.items.Item;
import engine.positions.GameMap;

/**
 * This class represents an Alien Bug in the game, an actor with 2
 * hit points that wanders around and picks up scraps. If the Alien Bug
 * is within the player's surroundings, it will start following the player,
 * stopping to pick up scraps along the way. It will continue to follow the player,
 * until the Alien Bug dies and drops its items.
 * @author Darryl Teh
 * @author Tan Zhong Yi
 * @version 1.0.0
 */
public class AlienBug extends Enemy {
    /**
     * Constructs a new AlienBug object. Adds the behaviours PickUpBehaviour,
     * FollowBehaviour and WanderBehaviours, which allows the AlienBug to
     * pick up scraps, follow the player and wander around.
     */
    public AlienBug() {
        super("Feature-" + RandomUtil.generateRandomInt(100,1000), 'a', 2);
        this.addCapability(Ability.ENTER_SPACESHIP);
        this.behaviours.put(0, new PickUpBehaviour());
        this.behaviours.put(1, new FollowBehaviour());
        this.behaviours.put(999, new WanderBehaviour());
    }

    /**
     * Drops all items in the AlienBug's inventory when it is defeated,
     * before removing it from the map and returning a string stating
     * its demise.
     *
     * @param actor the perpetrator
     * @param map where the actor fell unconscious
     * @return a string describing what happened when the actor is unconscious
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        List<Item> inventoryItems = getItemInventory();
        for (Item item : inventoryItems) {
            map.locationOf(this).addItem(item);
        }
        map.removeActor(this);
        return this + " met their demise at the hand of " + actor;
    }
}
