package game.items.template;

import engine.actors.Actor;

/**
 * The Buyable interface serves as a way for objects to be purchased,
 * if the objects are purchased they will deduct the credits from the
 * buyer.
 *
 * @author Yu Bin
 * @version 1.0.0
 */
public interface Buyable {
  /**
   * When called, if the player can afford to buy the item it will
   * deduct from the player's wallet and add it to the inventory.
   * @param actor the Player
   * @return a message for if the purchase is successful or not
   */
  String buyItem(Actor actor);
}
