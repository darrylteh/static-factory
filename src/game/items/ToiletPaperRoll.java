package game.items;

import engine.actions.ActionList;
import engine.actors.Actor;
import engine.items.Item;
import engine.positions.GameMap;
import engine.positions.Location;
import game.actions.SellAction;
import game.capabilities.Status;
import game.items.template.Buyable;
import game.items.template.Sellable;

/**
 * The ToiletPaperRoll class is an object that should be obtainable from
 * the ComputerTerminal class and serves no purpose other than it is an item.
 *
 * @author Yu Bin
 * @version 1.0.0
 */
public class ToiletPaperRoll extends Item implements Buyable, Sellable {
  private static final int SELL_PRICE = 1;
  private static final double SPECIAL_SELL_CHANCE = 0.5;

  /**
   * Chance condition for special effect
   */
  private static final double BUYING_CHANCE = 0.75;
  /**
   * When chance condition is met, this will be its price
   */
  private static final int DISCOUNT_PRICE = 1;

  private static final int BUY_PRICE = 5;

  /**
   * Initializes a ToiletPaperRoll object and it's attributes
   */
  public ToiletPaperRoll()
  {
    super("Toilet Paper Roll", 's', true);
  }

  /**
   * When called, if the player can afford to buy the item it will
   * deduct from the player's wallet and add it to the inventory.
   * When special condition is met, it will decrease its price to 1
   *
   * @param actor the Player
   * @return a message for if the purchase is successful or not
   */
  @Override
  public String buyItem(Actor actor) {
    int currentPrice = BUY_PRICE;
    if (Math.random() < BUYING_CHANCE)
    {
      currentPrice = DISCOUNT_PRICE;
    }
    if (actor.getBalance() >= currentPrice)
    {
      actor.deductBalance(currentPrice);
      actor.addItemToInventory(this);
      return actor + " successfully purchased " + this + " for " + currentPrice + " credits.";
    }
    else {
      return "Insufficient credits";
    }
  }

  /**
   * Sells the ToiletPaperRoll.
   * The ToiletPaperRoll is removed from the actor's inventory, and its selling price
   * is added to the actor's balance if the sale is successful.
   * There is a 50% chance that the player is killed instantly upon selling the item.
   *
   * @param actor the actor selling the item
   * @param map the map of actor selling the item
   * @return a String stating the result of the item's sale
   */
  @Override
  public String sellItem(Actor actor, GameMap map) {
    if (Math.random() < SPECIAL_SELL_CHANCE) {
      return "Goodbye.\n" + actor.unconscious(map);
    }
    else {
      actor.addBalance(SELL_PRICE);
      actor.removeItemFromInventory(this);
      return actor + " sells " + this + " for " + SELL_PRICE + " credits";
    }
  }

  /**
   * List of allowable actions that the ToiletPaperRoll allows its owner do to other actor.
   * Allows the owner to sell it to a trader.
   *
   * @param otherActor the other actor (trader)
   * @param location the location of the other actor (trader)
   * @return an unmodifiable list of Actions
   */
  @Override
  public ActionList allowableActions(Actor otherActor, Location location){
    ActionList actions = new ActionList();
    if (otherActor.hasCapability(Status.TRADER))
    {
      actions.add(new SellAction(this));
    }
    return actions;
  }
}
