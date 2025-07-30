package game.items.consumable;

import game.actions.ConsumeAction;
import game.actions.SellAction;
import game.capabilities.Status;
import game.items.template.Consumable;
import game.items.template.Sellable;

import java.lang.Math;

import engine.actions.ActionList;
import engine.actors.Actor;
import engine.items.Item;
import engine.positions.GameMap;
import engine.positions.Location;

/**
 * This class represents a jar of pickles in the game.
 * This jar of pickles can be picked up or dropped by the player.
 * Jar of pickles are represented by 'n' on the game map.
 * @author Nah Xin Wen
 * @version 1.0.0
 */
public class JarOfPickles extends Item implements Consumable, Sellable {
  private static final int SELL_PRICE = 25;
  private static final int SPECIAL_SELL_PRICE = 50;
  private static final double SPECIAL_SELL_CHANCE = 0.5;
  private int consumeEffectValue;
  private String consumeVerb;
  /**
   * Construct a new JarOfPickles object
   */
  public JarOfPickles(){
    super("Jar of Pickles", 'n', true);
    this.consumeEffectValue = 1;
    this.consumeVerb = "heals";
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
   * It has 50% chance to heal 1 point else hurt 1 point
   *
   * @param actor the actor consuming the item
   * @return a special string
   */
  @Override
  public String consumeConsumable(Actor actor) {
    String result = "";
    if(healOrHurt()){
      actor.heal(this.getConsumeEffectValue());
      result += actor+" consumed ";
      result += "a normal " + this + ". " + actor +" regenerate " + this.getConsumeEffectValue() + " points";
    }
    else{
      actor.hurt(this.getConsumeEffectValue());
      result += actor + " consumed ";
      result += "an expired " + this + ". " + actor +" feels sick.";
    }
    actor.removeItemFromInventory(this);
    return result;
  }

  /**
   * It has 50% to return true,else false
   * It will assign different consumeVerb to this.consumeVerb
   * @return boolean true if the heal effect else false, hurt effect
   *
   */
  private boolean healOrHurt(){
    int rand = (int)(Math.random()*2);
    boolean heal = true;
    this.consumeVerb = "heals";
    if(rand < 1){
      heal = false;
      this.consumeVerb = "hurts";
    }
    return heal;
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
   * Sells the JarOfPickles.
   * The JarOfPickles is removed from the actor's inventory, and its selling price
   * is added to the actor's balance.
   * There is a 50% chance that the factory will pay double the price, paying 50 credits instead.
   *
   * @param actor the actor selling the item
   * @param map the map of actor selling the item
   * @return a String stating the result of the item's sale
   */
  @Override
  public String sellItem(Actor actor, GameMap map) {
    int sellPrice = SELL_PRICE;
    if (Math.random() < SPECIAL_SELL_CHANCE) {
      sellPrice = SPECIAL_SELL_PRICE;
    }
    actor.addBalance(sellPrice);
    actor.removeItemFromInventory(this);
    return actor + " sells " + this + " for " + sellPrice + " credits";
  }

  /**
   * List of allowable actions that the JarOfPickles allows its owner do to other actor.
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
