import org.junit.Test;

import cs3500.hw03.FreecellController;
import cs3500.hw02.FreecellModel;
import cs3500.hw04.FreecellModelCreator;
import cs3500.hw04.FreecellMultiMoveModel;
import cs3500.hw02.PileType;

import java.io.StringReader;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the multiple card moving Freecell Model. Tests all of the new implementations that
 * differ from the single card moving Freecell model, such as moving multiple cards, making sure the
 * model throws exceptions with invalid builds and too few open piles, and makes sure that the
 * controller is also functional with this new model.
 */

public class FreecellMultiMoveModelTest {

  FreecellModelCreator creator1 = new FreecellModelCreator();
  FreecellModel multiMove1 = new FreecellMultiMoveModel();


  // To test that the Freecell Model creator will create a single move game
  // Tests by creating a Freecell single-move model, then checks to see if moving multiple cards
  // will throw an exception
  @Test(expected = IllegalArgumentException.class)
  public void testCreatorSingleMove() {
    FreecellModel singleModel1 = creator1.create(FreecellModelCreator.GameType.SINGLEMOVE);
    singleModel1.startGame(singleModel1.getDeck(), 8, 4, false);

    // Attempt to move multiple cards
    singleModel1.move(PileType.CASCADE, 2, 5, PileType.CASCADE, 7);
  }

  // To test that the Freecell Model creator will create a multi move game
  // Tests by creating a Freecell multi-move model, then attempts to move multiple cards and should
  // throw an exception for having an invalid build, which is only thrown in the multi move model
  @Test(expected = IllegalArgumentException.class)
  public void testCreatorMultiMove() {
    FreecellModel multiModel1 = creator1.create(FreecellModelCreator.GameType.MULTIMOVE);
    multiModel1.startGame(multiModel1.getDeck(), 8, 4, false);

    // Attempt to move multiple cards and should throw an error for having an invalid build
    multiModel1.move(PileType.CASCADE, 2, 5, PileType.CASCADE, 7);
  }


  // To test that the multi-move model will throw an exception if move is given an
  // invalid pile number
  @Test(expected = IllegalArgumentException.class)
  public void testMultiMoveInvalidPileNumber() {
    multiMove1.startGame(multiMove1.getDeck(), 8, 4, false);
    multiMove1.move(PileType.CASCADE, 9, 0, PileType.CASCADE, 5);
  }

  // To test that the multi-move model will throw an exception if move is given an
  // invalid card index
  @Test(expected = IllegalArgumentException.class)
  public void testMultiMoveInvalidCardIndex() {
    multiMove1.startGame(multiMove1.getDeck(), 8, 4, false);
    multiMove1.move(PileType.CASCADE, 6, -1, PileType.CASCADE, 5);
  }

  // To test that the multi-move model will throw an exception if move is given an
  // invalid destination pile number
  @Test(expected = IllegalArgumentException.class)
  public void testMultiMoveInvalidDestPile() {
    multiMove1.startGame(multiMove1.getDeck(), 8, 4, false);
    multiMove1.move(PileType.CASCADE, 6, 0, PileType.CASCADE, 8);
  }

  // To test that the multi-move will throw an exception if the moving pile from the cascade pile
  // has cards that violate the rule of alternating colors
  @Test(expected = IllegalArgumentException.class)
  public void testMultiMoveCardColorsDoNotMatch() {
    multiMove1.startGame(multiMove1.getDeck(), 8, 4, false);
    multiMove1.move(PileType.CASCADE, 2, 5, PileType.CASCADE, 7);
  }

  // To test that the multi-move will throw an exception if the moving pile from the cascade pile
  // has cards that violate the rule of descending card values
  @Test(expected = IllegalArgumentException.class)
  public void testMultiMoveCardIncorrectValues() {
    multiMove1.startGame(multiMove1.getDeck(), 8, 4, false);

    multiMove1.move(PileType.CASCADE, 2, 4, PileType.CASCADE, 3);
  }

  // To test that the multi-move will throw an exception if the amount of intermediate piles is too
  // low and the multiple cards cannot be moved
  @Test(expected = IllegalArgumentException.class)
  public void testIntermediatePilesTooLow() {
    multiMove1.startGame(multiMove1.getDeck(), 8, 4, false);

    multiMove1.move(PileType.CASCADE, 0, 6, PileType.OPEN, 0);
    multiMove1.move(PileType.CASCADE, 0, 5, PileType.OPEN, 1);
    multiMove1.move(PileType.CASCADE, 6, 5, PileType.OPEN, 2);
    multiMove1.move(PileType.CASCADE, 6, 4, PileType.OPEN, 3);
    multiMove1.move(PileType.CASCADE, 6, 3, PileType.CASCADE, 4);

    // Move that will trigger the exception
    multiMove1.move(PileType.CASCADE, 4, 5, PileType.CASCADE, 0);
  }

  // To test that the multi-move will correctly move a multitude of cards from one cascade pile
  // to another
  @Test
  public void testMultiMoveMultipleCascadeCards() {
    multiMove1.startGame(multiMove1.getDeck(), 52, 4, false);
    multiMove1.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 14);
    multiMove1.move(PileType.CASCADE, 14, 0, PileType.CASCADE, 2);
    multiMove1.move(PileType.CASCADE, 2, 0, PileType.CASCADE, 16);

    String currentGameState = "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1:\n" +
            "C2: 2♣\n" +
            "C3:\n" +
            "C4: 4♣\n" +
            "C5: 5♣\n" +
            "C6: 6♣\n" +
            "C7: 7♣\n" +
            "C8: 8♣\n" +
            "C9: 9♣\n" +
            "C10: 10♣\n" +
            "C11: J♣\n" +
            "C12: Q♣\n" +
            "C13: K♣\n" +
            "C14: A♦\n" +
            "C15:\n" +
            "C16: 3♦\n" +
            "C17: 4♦, 3♣, 2♦, A♣\n" +
            "C18: 5♦\n" +
            "C19: 6♦\n" +
            "C20: 7♦\n" +
            "C21: 8♦\n" +
            "C22: 9♦\n" +
            "C23: 10♦\n" +
            "C24: J♦\n" +
            "C25: Q♦\n" +
            "C26: K♦\n" +
            "C27: A♥\n" +
            "C28: 2♥\n" +
            "C29: 3♥\n" +
            "C30: 4♥\n" +
            "C31: 5♥\n" +
            "C32: 6♥\n" +
            "C33: 7♥\n" +
            "C34: 8♥\n" +
            "C35: 9♥\n" +
            "C36: 10♥\n" +
            "C37: J♥\n" +
            "C38: Q♥\n" +
            "C39: K♥\n" +
            "C40: A♠\n" +
            "C41: 2♠\n" +
            "C42: 3♠\n" +
            "C43: 4♠\n" +
            "C44: 5♠\n" +
            "C45: 6♠\n" +
            "C46: 7♠\n" +
            "C47: 8♠\n" +
            "C48: 9♠\n" +
            "C49: 10♠\n" +
            "C50: J♠\n" +
            "C51: Q♠\n" +
            "C52: K♠";

    assertEquals(currentGameState, multiMove1.getGameState());
  }

  // To test that the multi-move will correctly move a single card from one cascade pile to another
  @Test
  public void testMultiMoveMoveSingleCascadeCard() {
    multiMove1.startGame(multiMove1.getDeck(), 8, 4, false);
    multiMove1.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
    multiMove1.move(PileType.CASCADE, 7, 4, PileType.OPEN, 1);

    multiMove1.move(PileType.CASCADE, 7, 3, PileType.CASCADE, 5);

    String currentGameState = "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♠\n" +
            "O2: A♠\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n" +
            "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n" +
            "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n" +
            "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n" +
            "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n" +
            "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠, 6♥\n" +
            "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n" +
            "C8: 8♣, 3♦, J♦";

    assertEquals(currentGameState, multiMove1.getGameState());
  }

  FreecellModel singleMove1 = new FreecellModel();

  Readable readable1 = new StringReader("");
  Readable readable2 = new StringReader("C1 1 C15 C15 1 C3 C3 1 C17 C1");
  Readable readable3 = new StringReader("C3 6 C8");
  Readable readable4 = new StringReader("C3 5 C4");
  Readable readable5 = new StringReader("C1 7 O1 C1 6 O2 C7 6 O3 C7 5 O4 C7 4 C5 C5 6 C1");
  Readable readable6 = new StringReader("C3 6 C8 C8 6 O1 C8 5 O2 C4 7 O3 C4 6 C8 C8 4 C6 C1");
  Readable readable7 = new StringReader("C3 5 C4 C8 6 O1 C8 5 O2 C4 7 O3 C4 6 C8 C8 4 C6 C1");
  Readable readable8 = new StringReader("C1 7 O1 C1 6 O2 C7 6 O3 C7 5 O4 C7 4 C5 C5 6 C1 " +
          "C8 6 C7 C8 5 F1 O2 1 F1 C5 6 C1 C1");

  Appendable appendable1 = new StringBuffer();

  // To test that a multi move input in the controller when playing a single game will throw
  // an exception
  @Test
  public void testSingleMoveWillThrowExceptionWhenAttemptMultiMove() {
    FreecellController controller = new FreecellController(readable2, appendable1);
    controller.playGame(singleMove1.getDeck(), singleMove1, 52, 4, false);

    assertEquals("Invalid move. Try again. Impossible move: Card index out of bounds.",
            appendable1.toString().substring(2662, 2729));
  }

  // To test that a valid multi move input in the controller when playing a multi move game will
  // not throw an exception, unlike the single model
  @Test
  public void testMultiMoveWithController() {
    FreecellController controller = new FreecellController(readable2, appendable1);
    controller.playGame(multiMove1.getDeck(), multiMove1, 52, 4, false);

    assertEquals(multiMove1.getGameState(), appendable1.toString().substring(3996, 4441));

  }

  // To test that the controller will produce the multi-move only message that the build is wrong
  // if the cards to be moved have an invalid build, where the colors do not match
  @Test
  public void testControllerAlternatingColorsException() {
    FreecellController controller = new FreecellController(readable3, appendable1);

    controller.playGame(multiMove1.getDeck(), multiMove1, 8, 4, false);
    assertEquals("Invalid move. Try again. Incorrect Build: Colors must alternate.",
            appendable1.toString().substring(805, 869));
  }

  // To test that the controller will produce the multi-move only message that the build is wrong
  // if the cards to be moved have an invalid build, where the values are not descending
  @Test
  public void testControllerDescendingValuesException() {
    FreecellController controller = new FreecellController(readable4, appendable1);

    controller.playGame(multiMove1.getDeck(), multiMove1, 8, 4, false);
    assertEquals("Invalid move. Try again. Incorrect Build: Values must be descending.",
            appendable1.toString().substring(805, 873));
  }

  // To test that the controller will produce the multi-move only message that there are too many
  // cards to be moved if the amount of intermediate piles is too low
  @Test
  public void testIntermidatePilesExceptionTestedInController() {
    FreecellController controller = new FreecellController(readable5, appendable1);

    controller.playGame(multiMove1.getDeck(), multiMove1, 8, 4, false);
    assertEquals("Invalid move. Try again. Impossible move: Too many cards to be moved.",
            appendable1.toString().substring(4783, 4852));
  }

  // To test that the multi-move will move many cards after throwing an exception before regarding
  // a problem with the color alternating build of the moving cards, shown via the controller
  @Test
  public void testWillAllowToInputAgainWhenColorBuildIsOff() {
    FreecellController controller = new FreecellController(readable6, appendable1);

    controller.playGame(multiMove1.getDeck(), multiMove1, 8, 4, false);
    assertEquals(multiMove1.getGameState(), appendable1.toString().substring(4862, 5126));
  }

  // To test that the multi-move will move many cards after throwing an exception before regarding
  // a problem with the descending value build of the moving cards, shown via the controller
  @Test
  public void testWillAllowToInputAgainWhenValueIsOff() {
    FreecellController controller = new FreecellController(readable7, appendable1);

    controller.playGame(multiMove1.getDeck(), multiMove1, 8, 4, false);
    assertEquals(multiMove1.getGameState(), appendable1.toString().substring(4866, 5130));
  }

  // To test that the multi-move will move many cards after throwing an exception before regarding
  // a problem with there being too few intermediate open piles, shown via the controller
  @Test
  public void testWillAllowToInputAgainWhenOpenPileAmountIsOff() {
    FreecellController controller = new FreecellController(readable8, appendable1);

    controller.playGame(multiMove1.getDeck(), multiMove1, 8, 4, false);
    assertEquals(multiMove1.getGameState(), appendable1.toString().substring(8017, 8280));

  }

}
