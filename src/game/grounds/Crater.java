package game.grounds;

import engine.positions.Ground;
import engine.positions.Location;
import game.spawners.template.SpawnableActor;

/**
 * The Crater class represents a type of Ground that can spawn actors. It has a spawner attribute of
 * type Spawnable, and spawns actors of that spawner at its location with a spawn chance each tick
 * of the game.
 * @author Darryl Teh
 * @version 1.0.0
 */
public class  Crater extends Ground {

    private SpawnableActor spawner;

    /**
     * Constructs a new Crater object with the specified Spawnable object.
     *
     * @param spawner the Spawnable spawner object of the actor to spawn
     */
    public Crater(SpawnableActor spawner) {
        super('u');
        this.spawner = spawner;
    }

    /**
     * Crater experiences the flow of time. Given a spawn chance and that there is no actor on the
     * crater, spawns an actor on the crater each tick.
     *
     * @param location the location of the crater
     */
    @Override
    public void tick(Location location) {
        if (spawner.canSpawn() && !location.containsAnActor()) {
            location.addActor(spawner.createSpawn());
        }
    }
}
