package game.items.special;

import game.actions.MonologueAction;
import game.capabilities.Status;
import game.items.template.Buyable;
import game.items.template.Monologuable;
import game.utility.RandomUtil;

import java.util.ArrayList;
import java.util.List;

import engine.actions.ActionList;
import engine.actors.Actor;
import engine.actors.attributes.BaseActorAttributes;
import engine.items.Item;
import engine.positions.Location;

/**
 * This class represents an AI device called Astley, which can be bought
 * by the computer terminal for 50 credits.
 * The intern must pay a subscription fee of 1 credit every 5 ticks to use Astley.
 * If the intern fails to pay the subscription fee, the intern will not be able to
 * interact with the device until they have more credits to pay the fee.
 *
 * @author Darryl Teh
 * @author Tan Zhong Yi
 * @version 1.0.0
 */
public class Astley extends Item implements Buyable, Monologuable
{
    private static final int BUY_PRICE = 50;
    private static final int INVENTORY_THRESHOLD = 10;
    private static final int BALANCE_THRESHOLD = 50;
    private static final int HEALTH_THRESHOLD = 2;
    private Subscription subscription;

    /**
     * Constructs a new Astley object.
     */
    public Astley()
    {
        super("Astley, an AI device", 'z', true);
        subscription = new Subscription(1, 5);
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

    /**
     * When called, returns a String monologue among one of Astley's monologue options.
     *
     * @param actor the actor listening to Astley
     * @return a String monologue among one of Astley's monologue options
     */
    @Override
    public String generateMonologue(Actor actor) {
        ArrayList<String> monologueOptions = new ArrayList<>();
        monologueOptions.add("The factory will never gonna give you up, valuable intern!");
        monologueOptions.add("We promise we never gonna let you down with a range of staff benefits.");
        monologueOptions.add("We never gonna run around and desert you, dear intern!");
        List<Item> inventory = actor.getItemInventory();
        int inventorySize = inventory.size();
        if (inventorySize > INVENTORY_THRESHOLD) {
            monologueOptions.add("We never gonna make you cry with unfair compensation.");
        }
        int balance = actor.getBalance();
        if (balance > BALANCE_THRESHOLD) {
            monologueOptions.add("Trust is essential in this business. We promise we never gonna say goodbye to a valuable intern like you.");
        }
        int health = actor.getAttribute(BaseActorAttributes.HEALTH);
        if (health < HEALTH_THRESHOLD) {
            monologueOptions.add("Don't worry, we never gonna tell a lie and hurt you, unlike those hostile creatures.");
        }
        int monologueOptionsSize = monologueOptions.size();
        int index = RandomUtil.generateRandomInt(0, monologueOptionsSize);
        String monologue = monologueOptions.get(index);
        return monologue;
    }

    /**
     * Informs Astley of the passage of time.
     * This method is called once per turn, if Astley is being carried.
     * The method increments tickCounter by 1, if tickCounter is a multiple
     * of 5, that means the subscription is due.
     * In that case, if the player can afford the subscription, they pay the fee.
     * If the player cannot afford the subscription, the subscription is paused
     * until the player finds more credits to pay the fee.
     *
     * @param currentLocation The location of the actor carrying Astley.
     * @param actor The actor carrying Astley.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (actor.hasCapability(Status.FACTORY_EMPLOYEE))
        {
            subscription.tick(actor);
        }
    }

    /**
     * List of allowable actions that Astley can perform to the current actor
     * If the owner of Astley has an active subscription, a MonologueAction is
     * added so that the owner can listen to Astley.
     *
     * @param owner the actor that owns Astley
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor owner){
        ActionList actions = new ActionList();
        if (subscription.isSubscriptionActive()) {
            actions.add(new MonologueAction(this));
        }
        return actions;
    }
}

