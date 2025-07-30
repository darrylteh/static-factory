package game.grounds;


import game.grounds.template.Tree;



/**
 * This class represents a Inheritree sapling in the game, a type of tree that drops small fruits
 * and matures into its mature stage after 6 ticks. Inheritree saplings are represented by 't' on
 * the game map.
 * Created by
 * @author Darryl Teh
 * Modified by
 * @author Nah Xin Wen
 * @version 1.0.0
 */
public class InheritreeSapling extends Tree {

    /**
     * Constructs a new InheritreeSapling object.
     */
    public InheritreeSapling() {
        super('t');
    }

}
