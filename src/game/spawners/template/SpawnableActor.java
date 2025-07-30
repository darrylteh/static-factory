package game.spawners.template;

import engine.actors.Actor;

/**
 * The SpawnableActor interface contains the methods necessary
 * for a spawner class that spawns actors.
 * It provides methods to create a new instance
 * of an actor and get the spawn chance of an actor.
 * @author Darryl Teh
 * @version 1.0.0
 */
public interface SpawnableActor {

    /**
     * Creates a new instance of an actor.
     *
     * @return A new instance of an actor.
     */
    Actor createSpawn();

    /**
     * Gets the spawn chance of an actor.
     *
     * @return The double value spawn chance of an actor.
     */
    double getSpawnChance();

    /**
     * Determines if a spawnable actor can be spawned.
     *
     * @return true if actor can be spawned, false otherwise.
     */
    boolean canSpawn();
}
