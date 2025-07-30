package game.spawners;

import engine.items.Item;
import game.items.consumable.LargeFruit;
import game.spawners.template.SpawnableItem;

/**
 * The LargeFruitSpawner class represents a spawner for LargeFruit items.
 * It implements the SpawnableItem interface and provides methods to create a new instance
 * of LargeFruit and get the spawn chance of LargeFruit.
 * @author Nah Xin Wen
 * @version 1.0.0
 */
public class LargeFruitSpawner implements SpawnableItem{
  private static final double CONSTANT_CHANCE = 0.2;

  /**
   * Creates a new instance of LargeFruit.
   *
   * @return A new instance of LargeFruit.
   */
  @Override
  public Item createItem() {
    return new LargeFruit();
  }

  /**
   * Gets the spawn chance of LargeFruit.
   *
   * @return The double value spawn chance of LargeFruit.
   */
  @Override
  public double getSpawnChance() {
    return CONSTANT_CHANCE;
  }

  /**
   * Determines if LargeFruit can be spawned.
   *
   * @return true if LargeFruit can be spawned, false otherwise.
   */
  @Override
  public boolean canSpawn() {
    return Math.random() < getSpawnChance();
  }
}
