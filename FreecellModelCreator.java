package cs3500.hw04;

import cs3500.hw02.FreecellModel;

/**
 * Creates a model for the game Freecell. There are two possible types of models that the creator
 * can make; either a single moving card model or a multiple moving card model.
 */

public class FreecellModelCreator {

  /**
   * Type for the two types of models for a game of Freecell. <br>
   *
   * <p>Singlemove: This model allows for the player to move only one card at a time. <br>
   * Multimove: This model allows for the player to move multiple cards from one cascade pile to
   * another.<br></p>
   */

  public enum GameType {
    SINGLEMOVE, MULTIMOVE;
  }

  /**
   * Creates either a single-move or multi-move FreecellModel.
   *
   * @param type the type of model to be created
   * @return either a single move or multi move Freecell model
   */

  public static FreecellModel create(GameType type) {

    if (type == GameType.SINGLEMOVE) {
      return new FreecellModel();
    } else {
      return new FreecellMultiMoveModel();
    }
  }

}
