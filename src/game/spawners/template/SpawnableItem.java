package game.spawners.template;

import engine.items.Item;
/**
 * The SpawnableItem interface contains the methods necessary
 * for a spawner class that spawns items.
 * It provides methods to create a new instance
 * of an item and get the spawn chance of an item.
 * @author Nah Xin Wen
 * @version 1.0.0
 */
public interface SpawnableItem {
  /**
   * Creates a new instance of an item.
   *
   * @return A new instance of an item.
   */
  Item createItem();

  /**
   * Gets the spawn chance of an item.
   *
   * @return The double value spawn chance of an item.
   */
  double getSpawnChance();

  /**
   * Determines if a spawnable item can be spawned.
   *
   * @return true if item can be spawned, false otherwise.
   */
  boolean canSpawn();
}
