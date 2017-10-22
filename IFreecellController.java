package cs3500.hw03;

import cs3500.hw02.FreecellOperations;
import java.util.List;

/**
 * This is the interface of the Freecell controller. It is parameterized over the Card type.
 */

public interface IFreecellController<Card> {

  /**
   * Starts a new game of Freecell using the provided model, number of cascade and open piles and
   * the provided deck. If shuffle is set to false, the deck must be used as-is, otherwise the deck
   * should be shuffled.
   *
   * @param deck        the standard deck used in the game
   * @param model       the model of the Freecell game
   * @param numCascades the number of cascade piles
   * @param numOpens    the number of open piles
   * @param shuffle     whether or not to shuffle the deck
   * @throws IllegalArgumentException if a null deck or model is passed to it
   * @throws IllegalStateException    if either of its Readable and Appendable objects have not been
   *                                  initialized (i.e if they are null)
   */

  void playGame(List<Card> deck, FreecellOperations<Card> model, int numCascades,
                       int numOpens, boolean shuffle);

}
