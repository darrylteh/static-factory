package game.spawners;

import engine.actors.Actor;
import game.actors.npcs.SuspiciousAstronaut;
import game.spawners.template.SpawnableActor;

/**
 * The SuspiciousAstronautSpawner class represents a spawner for SuspiciousAstronaut actors.
 *  It implements the SpawnableActor interface and provides methods to create a new instance
 *  of SuspiciousAstronaut and get the spawn chance of SuspiciousAstronaut.
 *  @author Tan Zhong Yi
 *  @version 1.0.0
 *
 */
public class SuspiciousAstronautSpawner implements SpawnableActor {
    private static final double CONSTANT_CHANCE = 0.05;

    /**
     * Creates a new instance of SuspiciousAstronaut.
     *
     * @return A new instance of SuspiciousAstronaut.
     */
    @Override
    public Actor createSpawn() {
        return new SuspiciousAstronaut();
    }

    /**
     * Gets the spawn chance of SuspiciousAstronaut.
     *
     * @return The double value spawn chance of SuspiciousAstronaut.
     */
    @Override
    public double getSpawnChance() {
        return CONSTANT_CHANCE;
    }

    /**
     * Determines if SuspiciousAstronaut can be spawned.
     *
     * @return true if SuspiciousAstronaut can be spawned, false otherwise.
     */
    @Override
    public boolean canSpawn() {
        return Math.random() < getSpawnChance();
    }
}


