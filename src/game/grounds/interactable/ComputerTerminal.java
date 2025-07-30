package game.grounds.interactable;

import game.actions.BuyAction;
import game.actions.TravelAction;
import game.items.template.Buyable;
import java.util.ArrayList;
import java.util.Map;

import engine.actions.ActionList;
import engine.actors.Actor;
import engine.positions.Ground;
import engine.positions.Location;

/**
 * The ComputerTerminal class serves as hub for the player to purchase
 * items, and allow the player to travel to different "planets" or maps.
 *
 * @author Yu Bin
 * @version 2.0.0
 */
public class ComputerTerminal extends Ground {
  protected Map<Location, String> locationMap;

  /**
   * Contains all the available items that can be bought
   */
  private ArrayList<Buyable> itemList;

  /**
   * Initializes the ComputerTerminal object and it's attributes
   *
   * @param itemList the list of items that can be bought from ComputerTerminal
   * @param locationMap the Map of locations that can be traveled to using ComputerTerminal
   */
  public ComputerTerminal(ArrayList<Buyable> itemList,  Map<Location, String> locationMap)
  {
    super('=');
    this.itemList = itemList;
    this.locationMap = locationMap;
  }

  /**
   * Can be used to add items to buy from the Terminal
   * @param item the item to be added
   */
  public void addItem(Buyable item)
  {
    itemList.add(item);
  }

  /**
   * Can be used to remove items to buy from the Terminal
   * @param item the item to be removed
   */
  public void removeItem(Buyable item)
  {
    itemList.remove(item);
  }

  /**
   * Gets the item list containing all the item that can be bought
   * @return An arraylist of buyable items
   */
  public ArrayList<Buyable> getItemList()
  {
    return itemList;
  }

  /**
   * This method will add the location you wish to teleport to
   * into the terminal
   * @param location the destination you wish to input
   * @param direction the name of the place
   */
  public void addLocation(Location location, String direction) {
    locationMap.put(location, direction);
  }

  /**
   * This method will remove the location from the list of teleportable
   * locations in the terminal
   * @param location the location you wish to remove
   */
  public void removeLocation(Location location)
  {
    locationMap.remove(location);
  }

  /**
   * Returns an Action list of items that can be bought.
   *
   * @param actor the Actor acting
   * @param location the current Location
   * @param direction the direction of the Ground from the Actor
   * @return a new collection of BuyAction
   */
  @Override
  public ActionList allowableActions(Actor actor, Location location, String direction) {
    ActionList actionList = new ActionList();
    for (Buyable item : getItemList())
    {
      actionList.add(new BuyAction(item));
    }
    for (Location destination : locationMap.keySet())
    {
      if (location.map() != destination.map())
      {
        actionList.add(new TravelAction(destination, locationMap.get(destination)));
      }
    }
    return actionList;
  }
}
