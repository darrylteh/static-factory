package game.utility;

/**
 * This class will be used for generating random
 * numbers
 *
 * @author Yu Bin
 * @version 1.0.0
 */
public class RandomUtil {

  /**
   * Generates random numbers between lower and upper bounds
   * @param lower (inclusive) the lower bound number for the generated integer
   * @param upper (exclusive) the upper bound number for the generated integer
   * @return randomly generated number as an integer
   */
  public static int generateRandomInt(int lower, int upper) {
    int number = (int) (Math.random() * (upper - lower)) + lower;
    return number;
  }
}
