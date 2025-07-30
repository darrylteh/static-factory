package game.spawners;

import engine.items.Item;
import game.items.consumable.SmallFruit;
import game.spawners.template.SpawnableItem;
/**
 * The SmallFruitSpawner class represents a spawner for SmallFruit items.
 * It implements the SpawnableItem interface and provides methods to create a new instance
 * of SmallFruit and get the spawn chance of SmallFruit.
 * @author Nah Xin Wen
 * @version 1.0.0
 */
public class SmallFruitSpawner implements SpawnableItem{
  private static final double CONSTANT_CHANCE = 0.3;

  /**
   * Creates a new instance of SmallFruit.
   *
   * @return A new instance of SmallFruit.
   */
  @Override
  public Item createItem() {
    return new SmallFruit();
  }

  /**
   * Gets the spawn chance of SmallFruit.
   *
   * @return The double value spawn chance of SmallFruit.
   */
  @Override
  public double getSpawnChance() {
    return CONSTANT_CHANCE;
  }

  /**
   * Determines if SmallFruit can be spawned.
   *
   * @return true if SmallFruit can be spawned, false otherwise.
   */
  @Override
  public boolean canSpawn() {
    return Math.random() < getSpawnChance();
  }
}

