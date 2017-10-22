import org.junit.Test;

import cs3500.hw02.Card;
import cs3500.hw03.FreecellController;
import cs3500.hw02.FreecellModel;

import java.io.StringReader;
import java.util.List;

import static org.junit.Assert.assertEquals;

// Note: Javadoc added in Assignment 4

/**
 * Tests for the Freecell Controller. Tests all possible cases such as dealing with a working
 * readable and appendable, making sure exceptions are thrown when user input is invalid, and making
 * sure that the Controller outputs what is expected when given valid input and is functional with
 * the model it utilizes.
 */

public class FreecellControllerTest {

  // Readable Examples
  Readable stringReader = new StringReader("");
  Readable stringReader2 = new StringReader("C1");
  Readable stringReader3 = new StringReader("q");
  Readable stringReader4 = new StringReader("Kristi");
  Readable stringReader5 = new StringReader("CKristi");
  Readable stringReader6 = new StringReader("FKristi");
  Readable stringReader7 = new StringReader("OKristi");
  Readable stringReader8 = new StringReader("C1 Kristi");
  Readable stringReader9 = new StringReader("C1 2 Kristi");
  Readable stringReader10 = new StringReader("C1 2 CKristi");
  Readable stringReader11 = new StringReader("C1 2 FKristi");
  Readable stringReader12 = new StringReader("C1 2 OKristi");
  Readable stringReader13 = new StringReader("C1 7 O1 C1");
  Readable stringReader14 = new StringReader("C0 3 O1");
  Readable stringReader15 = new StringReader("" +
          "C1 1 F1 C2 1 F1 C3 1 F1 C4 1 F1 C5 1 F1 C6 1 F1 C7 1 F1 C8 1 F1 C9 1 F1 C10 1 F1 " +
          "C11 1 F1 C12 1 F1 C13 1 F1 " +
          "C14 1 F2 C15 1 F2 C16 1 F2 C17 1 F2 C18 1 F2 C19 1 F2 C20 1 F2 C21 1 F2 C22 1 F2 " +
          "C23 1 F2 C24 1 F2 C25 1 F2 C26 1 F2 " +
          "C27 1 F3 C28 1 F3 C29 1 F3 C30 1 F3 C31 1 F3 C32 1 F3 C33 1 F3 C34 1 F3 C35 1 F3 " +
          "C36 1 F3 C37 1 F3 C38 1 F3 C39 1 F3 " +
          "C40 1 F4 C41 1 F4 C42 1 F4 C43 1 F4 C44 1 F4 C45 1 F4 C46 1 F4 C47 1 F4 C48 1 F4 " +
          "C49 1 F4 C50 1 F4 C51 1 F4 C52 1 F4");
  Readable stringReader16 = new StringReader("C1 6 hello yolo q");


  // Appendable Example
  StringBuffer appendable1 = new StringBuffer();

  // Freecell model and deck example
  FreecellModel freecellModel1 = new FreecellModel();
  List<Card> deck1 = freecellModel1.getDeck();

  // Invalid Controller examples
  FreecellController invalidController1 = new FreecellController(stringReader, null);
  FreecellController invalidController2 = new FreecellController(null, appendable1);

  // Controller examples
  FreecellController controller1 = new FreecellController(stringReader, appendable1);
  FreecellController controller2 = new FreecellController(stringReader2, appendable1);
  FreecellController controller3 = new FreecellController(stringReader3, appendable1);
  FreecellController controller4 = new FreecellController(stringReader4, appendable1);
  FreecellController controller5 = new FreecellController(stringReader5, appendable1);
  FreecellController controller6 = new FreecellController(stringReader6, appendable1);
  FreecellController controller7 = new FreecellController(stringReader7, appendable1);
  FreecellController controller8 = new FreecellController(stringReader8, appendable1);
  FreecellController controller9 = new FreecellController(stringReader9, appendable1);
  FreecellController controller10 = new FreecellController(stringReader10, appendable1);
  FreecellController controller11 = new FreecellController(stringReader11, appendable1);
  FreecellController controller12 = new FreecellController(stringReader12, appendable1);
  FreecellController controller13 = new FreecellController(stringReader13, appendable1);
  FreecellController controller14 = new FreecellController(stringReader14, appendable1);
  FreecellController controller15 = new FreecellController(stringReader15, appendable1);

  // To test that the controller will throw an Illegal State Exception if the appendable is not
  // initialized correctly
  @Test(expected = IllegalStateException.class)
  public void testInvalidAppendable() {
    invalidController1.playGame(deck1, freecellModel1, 8, 4, false);
  }

  // To test that the controller will throw an Illegal State Exception if the readable is not
  // initialized correctly
  @Test(expected = IllegalStateException.class)
  public void testInvalidReadable() {
    invalidController2.playGame(deck1, freecellModel1, 8, 4, false);
  }

  // To test that the controller will throw an Illegal Argument Exception if it is passed a null
  // deck
  @Test(expected = IllegalArgumentException.class)
  public void testNullDeckToController() {
    controller1.playGame(null, freecellModel1, 8, 4, false);
  }

  // To test that the controller will throw an Illegal Argument Exception if it is passed a null
  // model
  @Test(expected = IllegalArgumentException.class)
  public void testNullModelToController() {

    controller1.playGame(deck1, null, 8, 4, false);
  }

  // To test that the controller will tell the client that the game cannot be started if invalid
  // values are passed to the model
  @Test
  public void testGameCannotBeStarted() {
    controller1.playGame(deck1, freecellModel1, 8, 0, true);
    assertEquals("Could not start game.", this.appendable1.toString());
  }

  // To test that the controller displays the game state to the client. This test specifically
  // shows the game state right after starting a valid game
  @Test
  public void testDisplayGameStateToClient() {
    controller2.playGame(deck1, freecellModel1, 8, 4, false);
    assertEquals(freecellModel1.getGameState(), this.appendable1.toString());
  }

  // To test that the controller will quit the game if the client types 'q' or 'Q'.
  @Test
  public void testClientQuitGame() {
    controller3.playGame(deck1, freecellModel1, 8, 4, false);
    assertEquals(freecellModel1.getGameState() + "\n" +
            "Game quit prematurely.", this.appendable1.toString());
  }

  // To test that the controller will ask the client to re-input if it does not start with 'C', 'F'
  // or 'O' when looking for a source pile.
  @Test
  public void testReInputSourcePile() {
    controller4.playGame(deck1, freecellModel1, 8, 4, false);
    assertEquals(freecellModel1.getGameState() + " Please input the source pile again. " +
            "You must name a valid pile with 'C', 'O', or 'F'", this.appendable1.toString());
  }

  // To test that the controller will ask the client to re-input if it starts with a C, but gives
  // a value that cannot be parsed to an integer when looking for a source pile
  @Test
  public void testReInputIntegerCascadeSourcePile() {
    controller5.playGame(deck1, freecellModel1, 8, 4, false);
    assertEquals(freecellModel1.getGameState() +
            " Please input the source pile again. You must follow your " +
            "cascade pile with a valid number.", this.appendable1.toString());
  }

  // To test that the controller will ask the client to re-input if it starts with an F, but gives
  // a value that cannot be parsed to an integer when looking for a source pile
  @Test
  public void testReInputIntegerFoundationSourcePile() {
    controller6.playGame(deck1, freecellModel1, 8, 4, false);
    assertEquals(freecellModel1.getGameState() +
            " Please input the source pile again. You must follow your " +
            "foundation pile with a valid number.", this.appendable1.toString());
  }

  // To test that the controller will ask the client to re-input if it starts with an O,
  // but gives a value that cannot be parsed to an integer when looking for a source pile
  @Test
  public void testReInputIntegerOpenSourcePile() {
    controller7.playGame(deck1, freecellModel1, 8, 4, false);
    assertEquals(freecellModel1.getGameState() +
            " Please input the source pile again. You must follow your " +
            "open pile with a valid number.", this.appendable1.toString());
  }

  // To test that the controller will ask the client to re-input a valid card index if the client
  // does not put in a valid value that can be parsed to an integer
  @Test
  public void testReInputCardIndex() {
    controller8.playGame(deck1, freecellModel1, 8, 4, false);
    assertEquals(freecellModel1.getGameState() + freecellModel1.getGameState() +
            " Please input the card index value again.", this.appendable1.toString());
  }

  // To test that the controller will ask the client to re-input if it does not start with 'C', 'F'
  // or 'O' when looking for a destination pile
  @Test
  public void testReInputDestinationPile() {
    controller9.playGame(deck1, freecellModel1, 8, 4, false);
    assertEquals(freecellModel1.getGameState() + freecellModel1.getGameState() +
                    freecellModel1.getGameState() + " Please input the destination pile again. " +
                    "You must name a valid pile with 'C', 'O', or 'F'",
            this.appendable1.toString());
  }

  // To test that the controller will ask the client to re-input if it starts with a C
  // but gives a value that cannot be parsed to an integer when looking for a destination pile
  @Test
  public void testReInputCascadeDestinationPile() {
    controller10.playGame(deck1, freecellModel1, 8, 4, false);
    assertEquals(freecellModel1.getGameState() + freecellModel1.getGameState() +
                    freecellModel1.getGameState() + " Please input the destination pile again. " +
                    "You must follow your cascade pile with a valid number.",
            this.appendable1.toString());
  }

  // To test that the controller will ask the client to re-input if it starts with an F but gives
  // a value that cannot be parsed to an integer when looking for a destination pile
  @Test
  public void testReInputFoundationDestinationPile() {
    controller11.playGame(deck1, freecellModel1, 8, 4, false);
    assertEquals(freecellModel1.getGameState() + freecellModel1.getGameState() +
                    freecellModel1.getGameState() + " Please input the destination pile again. " +
                    "You must follow your foundation pile with a valid number.",
            this.appendable1.toString());
  }

  // To test that the controller will ask the client to re-input if it starts with an O but gives
  // a value that cannot be parsed to an integer when looking for a destination pile
  @Test
  public void testReInputOpenDestinationPile() {
    controller12.playGame(deck1, freecellModel1, 8, 4, false);
    assertEquals(freecellModel1.getGameState() + freecellModel1.getGameState() +
                    freecellModel1.getGameState() + " Please input the destination pile again. " +
                    "You must follow your open pile with a valid number.",
            this.appendable1.toString());
  }

  // To test that the controller will properly tell the model a move, when the client properly
  // sends a valid move to the game
  @Test
  public void testMoveGame() {

    controller13.playGame(deck1, freecellModel1, 8, 4, false);
    assertEquals(freecellModel1.getGameState(), this.appendable1.toString().substring(801, 1067));
  }

  // To test that the controller will ask the client to try again when the client inputs an
  // invalid move and the model throws an error
  @Test
  public void testTryAgainMove() {
    controller14.playGame(deck1, freecellModel1, 8, 4, false);
    assertEquals(freecellModel1.getGameState() + freecellModel1.getGameState() +
            freecellModel1.getGameState() + " Invalid move. Try again. " +
            "Invalid parameters given.", this.appendable1.toString());
  }

  // To test that the controller will tell the client that the game is over
  @Test
  public void testGameOver() {
    controller15.playGame(deck1, freecellModel1, 52, 4, false);
    assertEquals(freecellModel1.getGameState() + "\n" + "Game over.",
            this.appendable1.toString().substring(72552, 73053));
  }

}


