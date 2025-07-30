package game.grounds;

import game.grounds.template.Tree;

/**
 * This class represents a Inheritree young in the game,
 * and matures into its mature stage,which is Inheritree mature after 5 ticks. Inheritree sprout are represented by 'y' on
 * the game map.
 * @author Nah Xin Wen
 * @version 1.0.0
 */
public class InheritreeYoung extends Tree{
  /**
   * Constructs a new InheritreeYoung object.
   */
  public InheritreeYoung(){
    super('y');
  }

}
