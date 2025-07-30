package game.spawners;

import engine.actors.Actor;
import game.actors.npcs.AlienBug;
import game.spawners.template.SpawnableActor;

/**
 * The AlienBugSpawner class represents a spawner for AlienBug actors.
 * It implements the SpawnableActor interface and provides methods to create a new instance
 * of AlienBug and get the spawn chance of AlienBug.
 * @author Tan Zhong Yi
 * @version 1.0.0
 */
public class AlienBugSpawner implements SpawnableActor {
    private static final double CONSTANT_CHANCE = 0.10;

    /**
     * Creates a new instance of AlienBug.
     *
     * @return A new instance of AlienBug.
     */
    @Override
    public Actor createSpawn() {
        return new AlienBug();
    }

    /**
     * Gets the spawn chance of AlienBug.
     *
     * @return The double value spawn chance of AlienBug.
     */
    @Override
    public double getSpawnChance() {
        return CONSTANT_CHANCE;
    }

    /**
     * Determines if AlienBug can be spawned.
     *
     * @return true if AlienBug can be spawned, false otherwise.
     */
    @Override
    public boolean canSpawn() {
        return Math.random() < getSpawnChance();
    }
}
