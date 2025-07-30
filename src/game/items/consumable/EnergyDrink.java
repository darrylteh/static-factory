package game.items.consumable;

import engine.actions.ActionList;
import engine.actors.Actor;
import engine.items.Item;
import game.actions.ConsumeAction;
import game.items.template.Buyable;
import game.items.template.Consumable;

/**
 * The EnergyDrink class is an object that should be obtainable from
 * the ComputerTerminal class and serves as a healing consumable item.
 *
 * @author Yu Bin
 * @version 1.0.0
 */
public class EnergyDrink extends Item implements Buyable, Consumable {

  /**
   * Amount of healing that can be done
   */
  private int consumeEffectValue;
  /**
   * the verb to show its usage
   */
  private String consumeVerb;
  /**
   * Chance condition for special effect
   */
  private static final double BUYING_CHANCE = 0.2;
  /**
   * When chance condition is met, this will multiply with the price value
   */
  private static final int MULTIPLIER = 2;
  private static final int BUY_PRICE = 10;

  /**
   * Initializes an EnergyDrink object and it's attributes
   */
  public EnergyDrink()
  {
    super("Energy Drink", '*', true);
    this.consumeEffectValue = 1;
    this.consumeVerb = "drinks";
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
   *
   * @param actor the actor consuming the item
   * @return the consume verb
   */
  @Override
  public String consumeConsumable(Actor actor) {
    actor.heal(this.getConsumeEffectValue());
    actor.removeItemFromInventory(this);
    String result = actor + " " + consumeVerb + " " + this + ". " + actor + " feels energised.";
    return result;
  }

  /**
   * When called, if the player can afford to buy the item it will
   * deduct from the player's wallet and add it to the inventory.
   *
   * When special condition is met, it will increase the price based
   * on the multiplier and original price
   * @param actor the Player
   * @return a message for if the purchase is successful or not
   */
  @Override
  public String buyItem(Actor actor) {
    int currentPrice = BUY_PRICE;
    if (Math.random() < BUYING_CHANCE)
    {
      currentPrice *= MULTIPLIER;
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
}
