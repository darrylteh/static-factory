package game.grounds.abilities;

import engine.positions.Location;

/**
 *  The abstract class represents a type of ability.
 *
 * @author Nah Xin Wen
 * @version 1.0.0
 */
public abstract class GroundAbility {

  /**
   * Perform the implementation
   *
   * @param location the location of the tree
   * @param counter the counter of tree class
   */
  public abstract void perform(Location location, int counter);
}
