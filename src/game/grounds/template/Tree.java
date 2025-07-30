package game.grounds.template;


import game.grounds.abilities.*;

import java.util.Map;
import java.util.TreeMap;

import engine.positions.Ground;
import engine.positions.Location;


/**
 * The abstract Tree class represents a type of Ground that represents a tree in the game. It has a
 * counter attribute, to keep track of in-game time. Trees can drop fruits and mature into different
 * trees.
 * Created by
 * @author Darryl Teh
 *
 * Modified by
 * @author Nah Xin Wen
 * @version 1.0.0
 */
public abstract class Tree extends Ground {


    /**
     * The counter used for tracking the number of game rounds the tree has experienced.
     */
    protected int counter;

    /**
     * The TreeMap used for storing the ability of the Tree according to the priority
     */
    protected Map<Integer, GroundAbility> abilities = new TreeMap<>();

    /**
     * Constructs a Tree object with the specified display character.
     *
     * @param displayChar the character used to display the tree in the game
     */
    public Tree(char displayChar) {
        super(displayChar);
        counter = 0;
    }


    /**
     * Tree experiences the flow of time. Increases the counter by 1 each game round.
     * Besides that, it will use a for-each loop to get every ability of TreeMap and
     * execute it.
     *
     * @param location the location of the tree
     */
    @Override
    public void tick(Location location) {
        counter += 1;
        for(GroundAbility ability:abilities.values()){
            ability.perform(location, counter);
        }
    }

    /**
     * Add the ability to the TeeMap
     *
     * @param priority the priority of the ability
     * @param newAbility the new ability of the tree
     */
    public void addAbility(int priority, GroundAbility newAbility){
        abilities.put(priority, newAbility);
    }
}
