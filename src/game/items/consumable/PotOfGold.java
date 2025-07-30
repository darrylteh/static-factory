package game.items.consumable;

import engine.actions.ActionList;
import engine.actors.Actor;
import engine.items.Item;
import engine.positions.GameMap;
import engine.positions.Location;
import game.actions.ConsumeAction;
import game.actions.SellAction;
import game.capabilities.Status;
import game.items.template.Consumable;
import game.items.template.Sellable;

/**
 * This class represents a pot of gold in the game.
 * This pot of gold can be picked up or dropped by the player.
 * Pot of gold are represented by '$' on the game map.
 * @author Nah Xin Wen
 * @version 1.0.0
 */
public class PotOfGold extends Item implements Consumable, Sellable {
  private static final int SELL_PRICE = 500;
  private static final double SPECIAL_SELL_CHANCE = 0.25;

  private int consumeEffectValue;
  private String consumeVerb;

  /**
   * Constructs a new PotOfGold object
   */
  public PotOfGold(){
    super("Pot of Gold", '$', true);
    this.consumeEffectValue = 10;
    this.consumeVerb = "gains";
  }

  /**
   * Getter of consumeEffectValue
   * @return consumeEffectValue the value after consuming
   */
  public int getConsumeEffectValue() {
    return consumeEffectValue;
  }

  /**
   * Getter of consumeVerb
   *
   * @return consumeVerb the string that describes the action of consuming
   */
  public String getConsumeVerb() {
    return consumeVerb;
  }

  /**
   * Consumes the consumable item, applying its effect to the actor. The item is then removed from
   * the actor's inventory.
   * It will add 10 credits to the actor's wallet
   *
   * @param actor the actor consuming the item
   * @return a special string
   */
  @Override
  public String consumeConsumable(Actor actor) {
    actor.addBalance(this.getConsumeEffectValue());
    String result = actor + " empties out " + this +  " and gains " + this.getConsumeEffectValue()+" credits.";
    result += " The rest are withheld as tax by the factory.";
    actor.removeItemFromInventory(this);
    return result;
  }

  /**
   * Returns a list of allowable actions that the consumable can perform to the current actor. In
   * this case, a ConsumeAction to consume the consumable.
   *
   * @param owner the owner of the consumable
   * @return an unmodifiable list of Actions
   */
  @Override
  public ActionList allowableActions(Actor owner) {
    ActionList actions = new ActionList();
    actions.add(new ConsumeAction(this));
    return actions;
  }

  /**
   * Sells the PotOfGold.
   * The PotOfGold is removed from the actor's inventory, and its selling price
   * is normally added to the actor's balance.
   * There is a 25% chance that the item is taken directly without payment.
   *
   * @param actor the actor selling the item
   * @param map the map of actor selling the item
   * @return a String stating the result of the item's sale
   */
  @Override
  public String sellItem(Actor actor, GameMap map) {
    actor.removeItemFromInventory(this);
    if (Math.random() < SPECIAL_SELL_CHANCE) {
      return "Factory took " + this + " without paying anything";
    }
    else {
      actor.addBalance(SELL_PRICE);
      return actor + " sells " + this + " for " + SELL_PRICE + " credits";
    }
  }

  /**
   * List of allowable actions that the PotOfGold allows its owner do to other actor.
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
