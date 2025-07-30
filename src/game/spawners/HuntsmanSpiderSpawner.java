package game.spawners;

import engine.actors.Actor;
import game.actors.npcs.HuntsmanSpider;
import game.spawners.template.SpawnableActor;

/**
 * The HuntsmanSpiderSpawner class represents a spawner for HuntsmanSpider actors.
 * It implements the SpawnableActor interface and provides methods to create a new instance
 * of HuntsmanSpider and get the spawn chance of HuntsmanSpider.
 * @author Darryl Teh
 * @version 1.0.0
 */
public class HuntsmanSpiderSpawner implements SpawnableActor {
    private static final double CONSTANT_CHANCE = 0.05;

    /**
     * Creates a new instance of HuntsmanSpider.
     *
     * @return A new instance of HuntsmanSpider.
     */
    @Override
    public Actor createSpawn() {
        return new HuntsmanSpider();
    }

    /**
     * Gets the spawn chance of HuntsmanSpider.
     *
     * @return The double value spawn chance of HuntsmanSpider.
     */
    @Override
    public double getSpawnChance() {
        return CONSTANT_CHANCE;
    }

    /**
     * Determines if HuntsmanSpider can be spawned.
     *
     * @return true if HuntsmanSpider can be spawned, false otherwise.
     */
    @Override
    public boolean canSpawn() {
        return Math.random() < getSpawnChance();
    }
}
