package game.items.special;

import engine.actors.Actor;
import engine.displays.Display;

/**
 * This class represents a subscription.
 * A subscription has a subscription fee and a billing cycle.
 * Every tick that is a multiple of the billing cycle, the subscriber
 * of the subscription is charged with the subscription fee.
 * If the subscriber is not able to afford the fee, their subscription
 * is paused until they can pay it in the next tick that is a multiple
 * of the billing cycle.
 *
 * @author Darryl Teh
 * @author Tan Zhong Yi
 * @version 1.0.0
 */
public class Subscription {
    private int subscriptionFee;
    private int billingCycle;
    private int tickCounter;
    private boolean subscriptionActive;
    private Display display = new Display();

    /**
     * Constructs a new Subscription object.
     *
     * @param subscriptionFee the fee that is charged every billingCycle
     * @param billingCycle the frequency of ticks in which the subscription fee is charged
     */
    public Subscription(int subscriptionFee, int billingCycle) {
        this.subscriptionFee = subscriptionFee;
        this.billingCycle = billingCycle;
        tickCounter = 0;
        subscriptionActive = true;
    }

    /**
     * Informs Subscription of the passage of time.
     * The method increments tickCounter by 1, if tickCounter is a multiple
     * of the billingCycle, that means the subscription is due.
     * In that case, if the player can afford the subscription, they pay the fee.
     * If the player cannot afford the subscription, the subscription is paused
     * until the player finds more credits to pay the fee.
     *
     * @param subscriber The actor subscribing to the subscription
     */
    public void tick(Actor subscriber) {
        tickCounter += 1;
        if (tickCounter % billingCycle == 0)
        {
            if (subscriber.getBalance() >= subscriptionFee) {
                subscriber.deductBalance(subscriptionFee);
                display.println("Subscription payment received!");
                subscriptionActive = true;
            }
            else {
                display.println("Subscription paused due to insufficient balance!");
                subscriptionActive = false;
            }
        }
    }

    /**
     * Returns a boolean stating whether the subscription is active or not.
     *
     * @return true is subscription is active, false otherwise
     */
    public boolean isSubscriptionActive() {
        return subscriptionActive;
    }
}
