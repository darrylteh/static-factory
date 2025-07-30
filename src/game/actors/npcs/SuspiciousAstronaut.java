package game.actors.npcs;

import engine.weapons.IntrinsicWeapon;
import game.actors.templates.Enemy;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;

/**
 * This class represents a Suspicious Astronaut in the game, an actor with 99
 * hit points that wanders around and attacks the player, instantly killing them with
 * 100% accuracy if the player is within its surroundings.
 * @author Darryl Teh
 * @author Tan Zhong Yi
 * @version 1.0.0
 */
public class SuspiciousAstronaut extends Enemy {
    private static final int BASE_INTRINSIC_WEAPON_DAMAGE = Integer.MAX_VALUE;
    private static final int BASE_INTRINSIC_WEAPON_HIT_RATE = 100;

    /**
     * Constructs a new SuspiciousAstronaut object. Adds the AttackBehaviour and WanderBehaviour, enabling
     * the SuspiciousAstronaut to attack and wander.
     */
    public SuspiciousAstronaut() {
        super("Among Us", 'ඞ', 99);
        this.behaviours.put(0, new AttackBehaviour());
        this.behaviours.put(999, new WanderBehaviour());
    }

    /**
     * Creates and returns an intrinsic weapon. This method has been overridden to make the
     * SuspiciousAstronaut's attack deal massive damage, instantly killing the intern with 100% accuracy.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(BASE_INTRINSIC_WEAPON_DAMAGE, "bonks", BASE_INTRINSIC_WEAPON_HIT_RATE);
    }
}
