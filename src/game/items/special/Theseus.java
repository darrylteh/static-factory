package game.items.special;

import engine.actions.ActionList;
import engine.actors.Actor;
import engine.items.Item;
import engine.positions.GameMap;
import engine.positions.Location;
import engine.positions.NumberRange;
import game.actions.TravelAction;
import game.items.template.Buyable;
import game.utility.RandomUtil;

/**
 * The Theseus class when placed on the ground allows the player
 * to travel around the map, and not across maps
 *
 * @author Yu Bin
 * @version 1.0.0
 */
public class Theseus extends Item implements Buyable {
  private static final int BUY_PRICE = 100;
  private static final String MAP_NAME = "current map";

  /**
   * Initializes the Theseus object and it's attributes
   */
  public Theseus()
  {
    super("THESEUS", '^', true);
  }

  /**
   * This method will return a random Location inside the game map
   * that does not contain an Actor
   *
   * @param map The current map that Theseus is dropped on
   * @return a Location object within the map that does not have an actor on it
   */
  public Location randomCoordinates(GameMap map)
  {
    int x;
    int y;
    NumberRange xRange = map.getXRange();
    NumberRange yRange = map.getYRange();
    x = RandomUtil.generateRandomInt(xRange.min(), xRange.max());
    y = RandomUtil.generateRandomInt(yRange.min(), yRange.max());
    Location destination = map.at(x,y);
    return destination;
  }

  /**
   * List of allowable actions that can be performed on Theseus when it is on the ground
   * This action will allow the Player to teleport randomly around the map
   *
   * @param location the location of the ground on which the item lies
   * @return list of MoveActorAction
   */
  @Override
  public ActionList allowableActions(Location location) {
    ActionList actions = super.allowableActions(location);
    GameMap map = location.map();
    Location destination = randomCoordinates(map);
    actions.add(new TravelAction(destination, MAP_NAME));
    return actions;
  }

  /**
   * When called, if the player can afford to buy the item it will
   * deduct from the player's wallet and add it to the inventory.
   *
   * @param actor the Player
   * @return a message for if the purchase is successful or not, alternatively it can also return an Error message
   */
  @Override
  public String buyItem(Actor actor) {
    int currentPrice = BUY_PRICE;
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
}
