package game.actors.npcs;

import engine.weapons.IntrinsicWeapon;
import game.actors.templates.Enemy;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;

/**
 * This class represents a Huntsman Spider in the game, an actor with 1
 * hit point that wanders around and attacks the player with 1 damage
 * and 25% accuracy if the player is within its surroundings.
 * @author Darryl Teh
 * @version 1.0.0
 */
public class HuntsmanSpider extends Enemy {
    private static final int BASE_INTRINSIC_WEAPON_DAMAGE = 1;
    private static final int BASE_INTRINSIC_WEAPON_HIT_RATE = 25;

    /**
     * Constructs a new HuntsmanSpider object. Adds the AttackBehaviour and WanderBehaviour, enabling
     * the HuntsmanSpider to attack and wander.
     */
    public HuntsmanSpider() {
        super("Huntsman Spider", '8', 1);
        this.behaviours.put(0, new AttackBehaviour());
        this.behaviours.put(999, new WanderBehaviour());
    }

    /**
     * Creates and returns an intrinsic weapon. This method has been overridden to make the
     * HuntsmanSpider's attack deal 1 damage with 25% accuracy.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(BASE_INTRINSIC_WEAPON_DAMAGE, "bites", BASE_INTRINSIC_WEAPON_HIT_RATE);
    }
}
