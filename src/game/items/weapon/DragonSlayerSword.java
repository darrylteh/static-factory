package game.items.weapon;

import engine.actions.ActionList;
import engine.actors.Actor;
import engine.positions.Location;
import engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.capabilities.Status;
import game.items.template.Buyable;

/**
 * The DragonSlayerSword class is an object that should be obtainable from
 * the ComputerTerminal class and serves as a strong weapon item for the player.
 *
 * @author Yu Bin
 * @version 1.0.0
 */
public class DragonSlayerSword extends WeaponItem implements Buyable {
  /**
   * Chance condition for special effect
   */
  private static final double BUYING_CHANCE = 0.5;
  private static final int BUY_PRICE = 100;

  /**
   * Initializes a DragonSlayerSword object and it's attributes
   */
  public DragonSlayerSword()
  {
    super("Dragon Slayer Replica", 'x', 50, "attacks", 75);
  }

  /**
   * When called, if the player can afford to buy the item it will
   * deduct from the player's wallet and add it to the inventory.
   * When special condition is met, it deducts the balance of the player
   * without giving them the weapon
   *
   * @param actor the Player
   * @return a message for if the purchase is successful or not, alternatively it can also return an Error message
   */
  @Override
  public String buyItem(Actor actor) {
    int currentPrice = BUY_PRICE;

    if (Math.random() < BUYING_CHANCE) {
      if (actor.getBalance() >= currentPrice) {
        actor.deductBalance(currentPrice);
        return currentPrice + " credits are taken from " + actor + ", but " + actor + " doesn't receive anything in return!";
      }
    }
    else {
      if (actor.getBalance() >= currentPrice) {
        actor.deductBalance(currentPrice);
        actor.addItemToInventory(this);
        return actor + " successfully purchased " + this + " for " + currentPrice + " credits.";
      }
    }
    // if no purchase was done
    return "Insufficient credits";
  }

  /**
   * List of allowable actions that the MetalPipe allows its owner do to other actor. Returns a list
   * of Actions with AttackAction to allow the owner to attack an actor with the metal pipe.
   *
   * @param otherActor the other actor
   * @param location   the location of the other actor
   * @return an unmodifiable list of Actions
   */
  @Override
  public ActionList allowableActions(Actor otherActor, Location location) {
    ActionList actions = new ActionList();
    if (!otherActor.hasCapability(Status.TRADER)){
      actions.add(new AttackAction(otherActor, location.toString(), this));
    }
    return actions;
  }
}
