package game.actions;

import engine.actions.Action;
import engine.actors.Actor;
import engine.positions.GameMap;
import game.items.template.Buyable;
/**
 * This class represents an actor buying an object
 *
 * @author Yu Bin
 * @version 1.0.0
 */
public class BuyAction extends Action {

  /**
   * The object to be bought
   */
  private Buyable transactionItem;

  /**
   * Initializes the ComputerTerminal object and it's attributes
   * @param transactionItem the item to be bought
   */
  public BuyAction(Buyable transactionItem)
  {
    this.transactionItem = transactionItem;

  }



  /**
   * This action will deduct the balance from the user
   * purchasing this item and add it to the player's inventory
   * and return a message of the result.
   * @param actor The actor performing the action.
   * @param map The map the actor is on.
   * @return
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    String message = transactionItem.buyItem(actor);
    return message;
  }

  /**
   * Describe what action will be performed if BuyAction is chosen in the menu.
   *
   * @param actor The actor performing the attack
   * @return the action description to be displayed on the menu
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " purchases " + transactionItem + ".";
  }
}
