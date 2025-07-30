package game.actions;

import engine.actions.Action;
import engine.actors.Actor;
import engine.positions.GameMap;
import engine.positions.Location;

/**
 * This class will allow the player to traverse within
 * and outside of the current game map they're in.
 *
 * @author Yu Bin
 * @version 1.0.0
 */
public class TravelAction extends Action {

  /**
   * The location of the final destination
   */
  private Location destination;
  /**
   * The name of the final destination
   */
  private String direction;
  /**
   * The means to travel to the final destination
   */
  private String verb;

  /**
   * Non-default constructor of the TravelAction class
   * @param destination the final destination you wish to travel
   * @param verb the means to travel to the location
   * @param direction the name of the location
   */
  public TravelAction(Location destination, String verb, String direction){
    this.destination = destination;
    this.verb = verb;
    this.direction = direction;
  }

  /**
   * Non-default constructor of the TravelAction class
   * @param destination the final destination you wish to travel
   * @param direction the name of the location
   */
  public TravelAction(Location destination, String direction){
    this.destination = destination;
    this.verb = "travels";
    this.direction = direction;
  }

  /**
   * This method will either transport the Actor to the appropriate
   * destination and return a message when it is successful,
   * or an error message for when there's an actor already at that
   * destination
   * @param actor The actor performing the action.
   * @param map The map the actor is on.
   * @return the result of the action
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    if (destination.containsAnActor())
    {
      return "ERROR: TRAVEL HAS FAILED";
    }
    else{
      map.moveActor(actor, destination);
      return actor + " arrived at " + destination + " in " + direction;
    }
  }

  /**
   * a description of the action
   * @param actor The actor performing the action.
   * @return A string for the description of this action
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " " + verb + " to " + direction;
  }
}
