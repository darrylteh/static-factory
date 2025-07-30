package game.grounds.abilities;

import game.utility.RandomUtil;
import game.spawners.template.SpawnableItem;
import java.util.List;

import engine.items.Item;
import engine.positions.Exit;
import engine.positions.Location;



/**
 *  This class represents the ability of producing item(means fruit at here).
 *  It has two attributes, which are rate and product. It will choose the surrounding
 *  location randomly and drop the product on that location.
 *
 *
 * @author Nah Xin Wen
 * @version 1.0.0
 */
public class ProducingAbility extends GroundAbility {

  /**
   * The item which will be dropped
   */
  private SpawnableItem product;


  /**
   * CConstructor, construct a new ProducingAbility object
   *
   * @param newProduct the item which will be dropped
   */

  public ProducingAbility(SpawnableItem newProduct){
    product = newProduct;
  }

  /**
   * Perform the dropping product action
   *
   * @param location the location of the tree
   * @param counter the counter of tree class
   */
  public void perform(Location location, int counter){
    if (product.canSpawn()) {
      List<Exit> exits = location.getExits();
      Exit chosenExit = exits.get(RandomUtil.generateRandomInt(0,exits.size()));
      Item spawnedFruit = product.createItem();
      chosenExit.getDestination().addItem(spawnedFruit);
    }
  }

}
