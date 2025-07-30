package game.grounds.abilities;

import engine.positions.Location;
import game.grounds.template.Tree;

/**
 *  This class represents the ability of growing up.It has two attributes,
 *  which are nextStage and ticksToMature. It will mature the Tree after certain ticks.
 *
 * @author Nah Xin Wen
 * @version 1.0.0
 */
public class GrowingAbility extends GroundAbility{

  /**
   * The next stage of the current tree
   */
  private Tree nextStage;
  /**
   * The required ticks to mature the current tree.
   */

  private int ticksToMature;

  /**
   * Constructor, construct a new GrowingAbility object
   *
   * @param newTicks the required ticks to mature the tree
   * @param newStage the next stage of the tree.
   */

  public GrowingAbility(int newTicks, Tree newStage){
    ticksToMature = newTicks;
    nextStage = newStage;
  }

  /**
   * Perform the growing up action
   *
   * @param location the location of the tree
   * @param counter the counter of tree class
   */
  public void perform(Location location, int counter){
    if(counter == ticksToMature){
      location.setGround(nextStage);
    }
  }

}
