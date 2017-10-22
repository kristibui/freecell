package cs3500.hw04;

import cs3500.hw02.FreecellModel;
import cs3500.hw03.FreecellController;
import java.io.InputStreamReader;

/**
 * Runs the Freecell Game.
 */

public class FreecellMain {

  public static void main(String[] args) {

    // To play single card move
    FreecellModel freecell1 = new FreecellModel();

    // To play multi card move
    FreecellModel freecell2 = new FreecellMultiMoveModel();

    new FreecellController(new InputStreamReader(System.in), System.out).playGame(freecell1.getDeck(),
            freecell1, 8, 4, true);

  }

}
