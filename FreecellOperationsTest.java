import cs3500.hw02.FreecellModel;
import cs3500.hw02.Card;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

import cs3500.hw02.PileType;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotEquals;

// Note: Javadoc added in Assignment 4

/**
 * Tests for the single card moving Freecell Model. Tests all of the operations for the game
 * Freecell, such as creating a valid deck, making sure the deck is valid, all combinations of
 * moving cards from one particular deck to the next, producing an accurate game state
 * representation, starting the game, and throwing exceptions when any of these operations are
 * invalid or have invalid inputs.
 */

public class FreecellOperationsTest {

  // Card examples
  Card card1 = new Card(13, "heart");
  Card card2 = new Card(4, "diamond");

  // FreecellModel example
  private FreecellModel freecell1 = new FreecellModel();
  private List<Card> cardList = new ArrayList<Card>();

  // Test to show that illegal card values will throw an exception
  @Test(expected = IllegalArgumentException.class)
  public void testCardException1() {
    new Card(14, "spade");
  }

  // Test to show that illegal card values will throw an exception
  @Test(expected = IllegalArgumentException.class)
  public void testCardException2() {
    new Card(8, "joker");
  }

  // Test to retrieve the value of a card
  @Test
  public void testCardGetter1() {
    assertEquals(13, card1.getInt());
  }

  // Test to retrieve the suit of a card
  @Test
  public void testCardGetter2() {
    assertEquals("heart", card1.getSuit());
  }

  // Tests the method toString
  @Test
  public void testToStringCard1() {
    assertEquals("4♦", card2.toString());
  }

  // To test the method getDeck
  // Tests by calling getDeck and seeing if the size equals 52
  @Test
  public void testGetDeck() {
    assertEquals(52, this.freecell1.getDeck().size());
  }

  // To test the method getDeck
  // Tests by adding the cards created from getDeck to a set and checking that none of the values
  // are null and that there are no duplicates
  @Test
  public void testGetDeck2() {
    HashSet<Card> hashSetCard = new HashSet<Card>();
    List<Card> deckList = this.freecell1.getDeck();

    for (int i = 0; i < 52; i++) {
      assertEquals(false, deckList.get(i) == null);
      assertEquals(true, hashSetCard.add(deckList.get(i)));
    }
  }

  // Example of a deck that is incomplete
  List<Card> illegalDeck1 = new ArrayList<Card>();

  // Example of a deck that has duplicate cards
  List<Card> illegalDeck2 = new ArrayList<Card>();

  // Example of a deck that has more than 52 cards
  List<Card> illegalDeck3 = new ArrayList<Card>();

  /**
   * Updates an illegal deck with null values for testing.
   */

  private void deckWithNulls() {

    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);

    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);

    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);

    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);

    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);
    illegalDeck1.add(null);

    illegalDeck1.add(null);
    illegalDeck1.add(null);

  }

  /**
   * Updates an illegal deck with duplicate cards for testing.
   */

  private void deckWithDuplicates() {

    this.illegalDeck2.add(new Card(1, "club"));
    this.illegalDeck2.add(new Card(2, "club"));
    this.illegalDeck2.add(new Card(2, "club"));
    this.illegalDeck2.add(new Card(4, "club"));
    this.illegalDeck2.add(new Card(5, "club"));
    this.illegalDeck2.add(new Card(6, "club"));
    this.illegalDeck2.add(new Card(7, "club"));
    this.illegalDeck2.add(new Card(8, "club"));
    this.illegalDeck2.add(new Card(9, "club"));
    this.illegalDeck2.add(new Card(10, "club"));
    this.illegalDeck2.add(new Card(11, "club"));
    this.illegalDeck2.add(new Card(12, "club"));
    this.illegalDeck2.add(new Card(13, "club"));

    this.illegalDeck2.add(new Card(1, "diamond"));
    this.illegalDeck2.add(new Card(2, "diamond"));
    this.illegalDeck2.add(new Card(3, "diamond"));
    this.illegalDeck2.add(new Card(4, "diamond"));
    this.illegalDeck2.add(new Card(6, "diamond"));
    this.illegalDeck2.add(new Card(6, "diamond"));
    this.illegalDeck2.add(new Card(7, "diamond"));
    this.illegalDeck2.add(new Card(8, "diamond"));
    this.illegalDeck2.add(new Card(9, "diamond"));
    this.illegalDeck2.add(new Card(10, "diamond"));
    this.illegalDeck2.add(new Card(11, "diamond"));
    this.illegalDeck2.add(new Card(12, "diamond"));
    this.illegalDeck2.add(new Card(13, "diamond"));

    this.illegalDeck2.add(new Card(1, "heart"));
    this.illegalDeck2.add(new Card(2, "heart"));
    this.illegalDeck2.add(new Card(3, "heart"));
    this.illegalDeck2.add(new Card(4, "heart"));
    this.illegalDeck2.add(new Card(5, "heart"));
    this.illegalDeck2.add(new Card(6, "heart"));
    this.illegalDeck2.add(new Card(7, "heart"));
    this.illegalDeck2.add(new Card(8, "heart"));
    this.illegalDeck2.add(new Card(8, "heart"));
    this.illegalDeck2.add(new Card(10, "heart"));
    this.illegalDeck2.add(new Card(11, "heart"));
    this.illegalDeck2.add(new Card(12, "heart"));
    this.illegalDeck2.add(new Card(13, "heart"));

    this.illegalDeck2.add(new Card(3, "spade"));
    this.illegalDeck2.add(new Card(4, "spade"));
    this.illegalDeck2.add(new Card(5, "spade"));
    this.illegalDeck2.add(new Card(6, "spade"));
    this.illegalDeck2.add(new Card(7, "spade"));
    this.illegalDeck2.add(new Card(8, "spade"));
    this.illegalDeck2.add(new Card(10, "spade"));
    this.illegalDeck2.add(new Card(10, "spade"));
    this.illegalDeck2.add(new Card(11, "spade"));
    this.illegalDeck2.add(new Card(12, "spade"));
    this.illegalDeck2.add(new Card(13, "spade"));
    this.illegalDeck2.add(new Card(3, "spade"));
    this.illegalDeck2.add(new Card(4, "spade"));

  }

  /**
   * Updates an illegal deck with more than 52 cards for testing.
   */

  private void moreThanStandardDeck() {

    this.illegalDeck3.add(new Card(1, "club"));
    this.illegalDeck3.add(new Card(2, "club"));
    this.illegalDeck3.add(new Card(3, "club"));
    this.illegalDeck3.add(new Card(4, "club"));
    this.illegalDeck3.add(new Card(5, "club"));
    this.illegalDeck3.add(new Card(6, "club"));
    this.illegalDeck3.add(new Card(7, "club"));
    this.illegalDeck3.add(new Card(8, "club"));
    this.illegalDeck3.add(new Card(9, "club"));
    this.illegalDeck3.add(new Card(10, "club"));
    this.illegalDeck3.add(new Card(11, "club"));
    this.illegalDeck3.add(new Card(12, "club"));
    this.illegalDeck3.add(new Card(13, "club"));

    this.illegalDeck3.add(new Card(1, "diamond"));
    this.illegalDeck3.add(new Card(2, "diamond"));
    this.illegalDeck3.add(new Card(3, "diamond"));
    this.illegalDeck3.add(new Card(4, "diamond"));
    this.illegalDeck3.add(new Card(5, "diamond"));
    this.illegalDeck3.add(new Card(6, "diamond"));
    this.illegalDeck3.add(new Card(7, "diamond"));
    this.illegalDeck3.add(new Card(8, "diamond"));
    this.illegalDeck3.add(new Card(9, "diamond"));
    this.illegalDeck3.add(new Card(10, "diamond"));
    this.illegalDeck3.add(new Card(11, "diamond"));
    this.illegalDeck3.add(new Card(12, "diamond"));
    this.illegalDeck3.add(new Card(13, "diamond"));

    this.illegalDeck3.add(new Card(1, "heart"));
    this.illegalDeck3.add(new Card(2, "heart"));
    this.illegalDeck3.add(new Card(3, "heart"));
    this.illegalDeck3.add(new Card(4, "heart"));
    this.illegalDeck3.add(new Card(5, "heart"));
    this.illegalDeck3.add(new Card(6, "heart"));
    this.illegalDeck3.add(new Card(7, "heart"));
    this.illegalDeck3.add(new Card(8, "heart"));
    this.illegalDeck3.add(new Card(9, "heart"));
    this.illegalDeck3.add(new Card(10, "heart"));
    this.illegalDeck3.add(new Card(11, "heart"));
    this.illegalDeck3.add(new Card(12, "heart"));
    this.illegalDeck3.add(new Card(13, "heart"));

    this.illegalDeck3.add(new Card(1, "spade"));
    this.illegalDeck3.add(new Card(2, "spade"));
    this.illegalDeck3.add(new Card(3, "spade"));
    this.illegalDeck3.add(new Card(4, "spade"));
    this.illegalDeck3.add(new Card(5, "spade"));
    this.illegalDeck3.add(new Card(6, "spade"));
    this.illegalDeck3.add(new Card(7, "spade"));
    this.illegalDeck3.add(new Card(8, "spade"));
    this.illegalDeck3.add(new Card(9, "spade"));
    this.illegalDeck3.add(new Card(10, "spade"));
    this.illegalDeck3.add(new Card(11, "spade"));
    this.illegalDeck3.add(new Card(12, "spade"));
    this.illegalDeck3.add(new Card(13, "spade"));

    this.illegalDeck3.add(new Card(1, "club"));
    this.illegalDeck3.add(new Card(2, "club"));
    this.illegalDeck3.add(new Card(3, "club"));
    this.illegalDeck3.add(new Card(4, "club"));
    this.illegalDeck3.add(new Card(5, "club"));
    this.illegalDeck3.add(new Card(6, "club"));
    this.illegalDeck3.add(new Card(7, "club"));
    this.illegalDeck3.add(new Card(8, "club"));
    this.illegalDeck3.add(new Card(9, "club"));
    this.illegalDeck3.add(new Card(10, "club"));
    this.illegalDeck3.add(new Card(11, "club"));
    this.illegalDeck3.add(new Card(12, "club"));
    this.illegalDeck3.add(new Card(13, "club"));

  }

  // To test starting a game with an illegal deck that is incomplete
  @Test(expected = IllegalArgumentException.class)
  public void testStartGameIncompleteDeck() {
    deckWithNulls();
    freecell1.startGame(illegalDeck1, 8, 4, false);
  }

  // To test starting a game with an illegal deck that has duplicate cards
  @Test(expected = IllegalArgumentException.class)
  public void testStartGameDeckWithDuplicates() {
    deckWithDuplicates();
    freecell1.startGame(illegalDeck2, 6, 3, true);
  }

  // To test starting a game with an illegal deck that has more than 52 cards
  @Test(expected = IllegalArgumentException.class)
  public void testStartGameWithExceededDeck() {
    moreThanStandardDeck();
    freecell1.startGame(illegalDeck3, 5, 2, true);
  }

  // NOTE: Added in Assignment 3
  // To test that starting a game with less than 4 cascade piles throws an error
  @Test(expected = IllegalArgumentException.class)
  public void testLessThanFourCascadePiles() {
    freecell1.startGame(freecell1.getDeck(), 3, 2, false);
  }

  // NOTE: Added in Assignment 3
  // To test that starting a game with less than 1 open pile throws an error
  @Test(expected = IllegalArgumentException.class)
  public void testLessThanOneOpenPile() {
    freecell1.startGame(freecell1.getDeck(), 4, 0, false);
  }

  // String representation of the game state of example freecell1
  String initialDealNoShuffle = "F1:\n" +
          "F2:\n" +
          "F3:\n" +
          "F4:\n" +
          "O1:\n" +
          "O2:\n" +
          "O3:\n" +
          "O4:\n" +
          "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n" +
          "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n" +
          "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n" +
          "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n" +
          "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n" +
          "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n" +
          "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n" +
          "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠";

  // To test starting a game will properly deal cards from the deck in round robin fashion
  // to the cascade piles
  // Tests by calling startGame, then seeing if the state of the game is as expected
  @Test
  public void testStartGameDealNoShuffle() {
    freecell1.startGame(freecell1.getDeck(), 8, 4, false);
    assertEquals(freecell1.getGameState(), this.initialDealNoShuffle);
  }

  // To test starting a game and shuffling will deal cards in round robin fashion and produce
  // a different result than when no shuffle
  // Tests by calling startGame and having shuffle be true, then seeing if the state of the game
  // is different from when one would not have shuffled
  @Test
  public void testStartGameDealShuffle() {
    freecell1.startGame(freecell1.getDeck(), 8, 4, true);
    assertNotEquals(freecell1.getGameState(), this.initialDealNoShuffle);
  }

  // To test that starting a game and then re-dealing the deck / restarting the game will still
  // produce the same outcome
  // Tests by calling startGame and keeping track of the current game state, then calling
  // startGame again and comparing the current game state to the game state of the previous game
  @Test
  public void testStartGameNoShuffleDealAgain() {
    freecell1.startGame(freecell1.getDeck(), 8, 4, false);
    String firstDealing = freecell1.getGameState();

    freecell1.startGame(freecell1.getDeck(), 8, 4, false);
    assertEquals(firstDealing, freecell1.getGameState());
  }

  // Reference for move method test:
  // Game state of example freecell1 after calling startGame
  //  F1:
  //  F2:
  //  F3:
  //  F4
  //  O1:
  //  O2:
  //  O3:
  //  O4:
  //  C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠
  //  C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠
  //  C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠
  //  C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠
  //  C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠
  //  C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠
  //  C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠
  //  C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠

  // NOTE: Added in Assignment 3
  // To test moving from any pile to a non-empty foundation pile
  @Test
  public void testMoveFromPileToNonEmptyFoundation() {
    freecell1.startGame(freecell1.getDeck(), 8, 4, false);
    freecell1.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
    freecell1.move(PileType.CASCADE, 0, 6, PileType.OPEN, 1);
    freecell1.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 0);
    freecell1.move(PileType.CASCADE, 0, 5, PileType.FOUNDATION, 0);

    String currGameState = "F1: A♠, 2♠\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♠\n" +
            "O2: 10♠\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♣, 9♣, 4♦, Q♦, 7♥\n" +
            "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n" +
            "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n" +
            "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n" +
            "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n" +
            "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n" +
            "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n" +
            "C8: 8♣, 3♦, J♦, 6♥";

    assertEquals(currGameState, freecell1.getGameState());
  }

  // To test moving from a cascade pile to an open pile
  // Tests by calling move, then comparing the expected game state
  @Test
  public void testMoveFromCascadeToOpen() {
    freecell1.startGame(freecell1.getDeck(), 8, 4, false);
    freecell1.move(PileType.CASCADE, 0, 6, PileType.OPEN, 0);

    String currGameState = "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 10♠\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠\n" +
            "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n" +
            "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n" +
            "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n" +
            "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n" +
            "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n" +
            "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n" +
            "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠";

    assertEquals(currGameState, freecell1.getGameState());
  }

  // To test moving from a cascade pile to a foundation pile
  // Tests by calling move to move one card to an open pile, and then moving an ace
  // card to a free foundation pile, then comparing the expected game state
  @Test
  public void testMoveFromCascadeToFoundation() {
    freecell1.startGame(freecell1.getDeck(), 8, 4, false);
    freecell1.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
    freecell1.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 0);

    String currGameState = "F1: A♠\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♠\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n" +
            "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n" +
            "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n" +
            "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n" +
            "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n" +
            "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n" +
            "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n" +
            "C8: 8♣, 3♦, J♦, 6♥";

    assertEquals(currGameState, freecell1.getGameState());
  }

  // To test moving from a cascade pile to a cascade pile
  // Tests by moving one card to an open pile, moving an ace to a free foundation pile,
  // then moving a valid card from one cascade pile to another, then comparing the game state
  @Test
  public void testMoveFromCascadeToCascade() {
    freecell1.startGame(freecell1.getDeck(), 8, 4, false);
    freecell1.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
    freecell1.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 0);

    freecell1.move(PileType.CASCADE, 7, 3, PileType.CASCADE, 5);

    String currGameState = "F1: A♠\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♠\n" +
            "O2:\n" +
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

    assertEquals(currGameState, freecell1.getGameState());
  }

  // To test moving from one open pile to another
  // Tests by moving one card into an open pile, then moving that card into another open pile,
  // then comparing the game state
  @Test
  public void testMoveFromOpenToOpen() {
    freecell1.startGame(freecell1.getDeck(), 8, 4, false);
    freecell1.move(PileType.CASCADE, 0, 6, PileType.OPEN, 0);
    freecell1.move(PileType.OPEN, 0, 0, PileType.OPEN, 1);

    String currGameState = "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2: 10♠\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠\n" +
            "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n" +
            "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n" +
            "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n" +
            "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n" +
            "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n" +
            "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n" +
            "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠";

    assertEquals(currGameState, freecell1.getGameState());
  }

  // To test moving from an open pile to a foundation pile
  // Tests by moving one card to an open pile, another card to an open pile, then moving the last
  // card into a foundation pile, then comparing the game state
  @Test
  public void testMoveFromOpenToFoundation() {
    freecell1.startGame(freecell1.getDeck(), 8, 4, false);
    freecell1.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
    freecell1.move(PileType.CASCADE, 7, 4, PileType.OPEN, 1);
    freecell1.move(PileType.OPEN, 1, 0, PileType.FOUNDATION, 0);

    String currGameState = "F1: A♠\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♠\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n" +
            "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n" +
            "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n" +
            "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n" +
            "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n" +
            "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n" +
            "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n" +
            "C8: 8♣, 3♦, J♦, 6♥";

    assertEquals(currGameState, freecell1.getGameState());
  }

  // To test moving from an open pile to a cascade pile
  // Tests by moving three cards from the same cascade pile into different open piles, then moving
  // the last card from the open pile back to a valid cascade pile, then comparing the game state
  @Test
  public void testMoveFromOpenToCascade() {
    freecell1.startGame(freecell1.getDeck(), 8, 4, false);
    freecell1.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
    freecell1.move(PileType.CASCADE, 7, 4, PileType.OPEN, 1);
    freecell1.move(PileType.CASCADE, 7, 3, PileType.OPEN, 2);

    freecell1.move(PileType.OPEN, 2, 0, PileType.CASCADE, 5);

    String currGameState = "F1:\n" +
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

    assertEquals(currGameState, freecell1.getGameState());
  }

  // To test that attempting to move a card with an invalid cascade pile index throws
  // an error
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidParametersMove1() {
    freecell1.startGame(freecell1.getDeck(), 8, 4, false);
    freecell1.move(PileType.CASCADE, -1, 6, PileType.CASCADE, 4);
  }

  // To test that attempting to move a card with an invalid card index for a cascade pile throws
  // an error
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidParametersMove2() {
    freecell1.startGame(freecell1.getDeck(), 8, 4, false);
    freecell1.move(PileType.CASCADE, 3, -1, PileType.CASCADE, 4);
  }

  // To test that attempting to move a card with an invalid destination pile index throws an error
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidParametersMove3() {
    freecell1.startGame(freecell1.getDeck(), 8, 4, false);
    freecell1.move(PileType.CASCADE, 2, 4, PileType.CASCADE, -1);
  }

  // To test that attempting to move a card with an invalid cascade pile index larger than the
  // amount of cascade piles existing within the game
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPileNumberCascade() {
    freecell1.startGame(freecell1.getDeck(), 8, 4, false);
    freecell1.move(PileType.CASCADE, 10, 4, PileType.OPEN, 3);
  }

  // To test that attempting to move a card with an index that is not the last card in the cascade
  // pile throws an error (when the card index is higher than the
  // index of the last card in the pile)
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCardIndexCascade1() {
    freecell1.startGame(freecell1.getDeck(), 8, 4, false);
    freecell1.move(PileType.CASCADE, 1, 8, PileType.OPEN, 2);
  }

  // To test that attempting to move a card with an index that is not the last card in the
  // cascade pile throws an error (when the card index is lower than the
  // index of the last card in the pile)
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCardIndexCascade2() {
    freecell1.startGame(freecell1.getDeck(), 8, 4, false);
    freecell1.move(PileType.CASCADE, 0, 6, PileType.CASCADE, 1);
  }

  // To test that attempting to move a card from an open pile that does not exist throws an error
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPileNumberOpen() {
    freecell1.startGame(freecell1.getDeck(), 8, 4, false);
    freecell1.move(PileType.OPEN, 5, 0, PileType.CASCADE, 2);
  }

  // To test that attempting to move a card from an empty open pile throws an error
  @Test(expected = IllegalArgumentException.class)
  public void testNoCardsInOpen() {
    freecell1.startGame(freecell1.getDeck(), 8, 4, false);
    freecell1.move(PileType.OPEN, 0, 0, PileType.CASCADE, 3);
  }

  // To test that attempting to move a card with an card index that is not at 0 from an open pile
  // throws an error
  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectCardIndexOpen() {
    freecell1.startGame(freecell1.getDeck(), 8, 4, false);
    freecell1.move(PileType.OPEN, 0, 3, PileType.CASCADE, 4);
  }

  // To test that attempting to move a card from the foundation pile throws an error
  @Test(expected = IllegalArgumentException.class)
  public void testFoundationCardRemoval() {
    freecell1.startGame(freecell1.getDeck(), 8, 4, false);
    freecell1.move(PileType.FOUNDATION, 2, 0, PileType.CASCADE, 3);
  }

  // To test that attempting to move a card to a cascade pile that does not exist throws an error
  @Test(expected = IllegalArgumentException.class)
  public void testCascadePileTooBig() {
    freecell1.startGame(freecell1.getDeck(), 8, 4, false);
    freecell1.move(PileType.CASCADE, 0, 6, PileType.CASCADE, 9);
  }

  // To test that attempting to move a card to a cascade pile when the card is not the opposite
  // color of the last card in the pile throws an error
  @Test(expected = IllegalArgumentException.class)
  public void testNoMatchingColorsCascade() {
    freecell1.startGame(freecell1.getDeck(), 8, 4, false);
    freecell1.move(PileType.CASCADE, 0, 6, PileType.CASCADE, 1);
  }

  // To test that attempting to move a card to a cascade pile when the card value is not one
  // lower than the last card in the pile throws an error
  @Test(expected = IllegalArgumentException.class)
  public void testValueNotOneLowerCascade() {
    freecell1.startGame(freecell1.getDeck(), 8, 4, false);
    freecell1.move(PileType.CASCADE, 4, 5, PileType.OPEN, 2);
    freecell1.move(PileType.CASCADE, 5, 5, PileType.CASCADE, 4);
  }

  // To test that attempting to move a card to a foundation pile when the destination pile number
  // is greater than 3 throws an error
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalDestPileNumberFoundation() {
    freecell1.startGame(freecell1.getDeck(), 8, 4, false);
    freecell1.move(PileType.CASCADE, 0, 6, PileType.FOUNDATION, 5);
  }

  // To test that attempting to move a card to a foundation pile when the moving card is not an ace
  // throws an error
  @Test(expected = IllegalArgumentException.class)
  public void testEmptyFoundationPileNotAnAce() {
    freecell1.startGame(freecell1.getDeck(), 8, 4, false);
    freecell1.move(PileType.CASCADE, 0, 6, PileType.FOUNDATION, 0);
  }

  // To test that attempting to move a card to a foundation pile when the moving card is not of the
  // same type of suit as the pile throws an error
  @Test(expected = IllegalArgumentException.class)
  public void testNonEmptyFoundationCardValuesNotSameSuit() {
    freecell1.startGame(freecell1.getDeck(), 8, 4, false);
    freecell1.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
    freecell1.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 0);

    freecell1.move(PileType.CASCADE, 7, 3, PileType.FOUNDATION, 0);
  }

  // To test that attempting to move a card to a foundation pile when the moving card's value is
  // not one greater than the last card in the foundation pile throws an error
  @Test(expected = IllegalArgumentException.class)
  public void testNonEmptyFoundationCardValuesNotOneValueHigher() {
    freecell1.startGame(freecell1.getDeck(), 8, 4, false);
    freecell1.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
    freecell1.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 0);
    freecell1.move(PileType.CASCADE, 0, 6, PileType.FOUNDATION, 0);
  }

  // To test that attempting to move a card to an open pile when the destination pile is too high
  // throws an error (when the open pile does not exist)
  @Test(expected = IllegalArgumentException.class)
  public void testOpenPileTooBig() {
    freecell1.startGame(freecell1.getDeck(), 8, 4, false);
    freecell1.move(PileType.CASCADE, 0, 6, PileType.OPEN, 5);
  }

  // To test that attempting to move a card to an open pile when the open pile is not empty
  // throws an error
  @Test(expected = IllegalArgumentException.class)
  public void testOpenPileNonEmpty() {
    freecell1.startGame(freecell1.getDeck(), 8, 4, false);
    freecell1.move(PileType.CASCADE, 0, 6, PileType.OPEN, 0);
    freecell1.move(PileType.CASCADE, 0, 5, PileType.OPEN, 0);
  }

  // To test that a game is not over if the size of the foundation piles do not sum to 52
  @Test
  public void testGameIsNotOver() {
    freecell1.startGame(freecell1.getDeck(), 8, 4, false);
    assertEquals(false, freecell1.isGameOver());
  }

  // NOTE: Added in Assignment 3
  // To test that isGameOver returns true when the game is over
  @Test
  public void testGameIsOver() {
    freecell1.startGame(freecell1.getDeck(), 52, 1, false);

    freecell1.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    freecell1.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION, 0);
    freecell1.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION, 0);
    freecell1.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 0);
    freecell1.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 0);
    freecell1.move(PileType.CASCADE, 5, 0, PileType.FOUNDATION, 0);
    freecell1.move(PileType.CASCADE, 6, 0, PileType.FOUNDATION, 0);
    freecell1.move(PileType.CASCADE, 7, 0, PileType.FOUNDATION, 0);
    freecell1.move(PileType.CASCADE, 8, 0, PileType.FOUNDATION, 0);
    freecell1.move(PileType.CASCADE, 9, 0, PileType.FOUNDATION, 0);
    freecell1.move(PileType.CASCADE, 10, 0, PileType.FOUNDATION, 0);
    freecell1.move(PileType.CASCADE, 11, 0, PileType.FOUNDATION, 0);
    freecell1.move(PileType.CASCADE, 12, 0, PileType.FOUNDATION, 0);

    freecell1.move(PileType.CASCADE, 13, 0, PileType.FOUNDATION, 1);
    freecell1.move(PileType.CASCADE, 14, 0, PileType.FOUNDATION, 1);
    freecell1.move(PileType.CASCADE, 15, 0, PileType.FOUNDATION, 1);
    freecell1.move(PileType.CASCADE, 16, 0, PileType.FOUNDATION, 1);
    freecell1.move(PileType.CASCADE, 17, 0, PileType.FOUNDATION, 1);
    freecell1.move(PileType.CASCADE, 18, 0, PileType.FOUNDATION, 1);
    freecell1.move(PileType.CASCADE, 19, 0, PileType.FOUNDATION, 1);
    freecell1.move(PileType.CASCADE, 20, 0, PileType.FOUNDATION, 1);
    freecell1.move(PileType.CASCADE, 21, 0, PileType.FOUNDATION, 1);
    freecell1.move(PileType.CASCADE, 22, 0, PileType.FOUNDATION, 1);
    freecell1.move(PileType.CASCADE, 23, 0, PileType.FOUNDATION, 1);
    freecell1.move(PileType.CASCADE, 24, 0, PileType.FOUNDATION, 1);
    freecell1.move(PileType.CASCADE, 25, 0, PileType.FOUNDATION, 1);

    freecell1.move(PileType.CASCADE, 26, 0, PileType.FOUNDATION, 2);
    freecell1.move(PileType.CASCADE, 27, 0, PileType.FOUNDATION, 2);
    freecell1.move(PileType.CASCADE, 28, 0, PileType.FOUNDATION, 2);
    freecell1.move(PileType.CASCADE, 29, 0, PileType.FOUNDATION, 2);
    freecell1.move(PileType.CASCADE, 30, 0, PileType.FOUNDATION, 2);
    freecell1.move(PileType.CASCADE, 31, 0, PileType.FOUNDATION, 2);
    freecell1.move(PileType.CASCADE, 32, 0, PileType.FOUNDATION, 2);
    freecell1.move(PileType.CASCADE, 33, 0, PileType.FOUNDATION, 2);
    freecell1.move(PileType.CASCADE, 34, 0, PileType.FOUNDATION, 2);
    freecell1.move(PileType.CASCADE, 35, 0, PileType.FOUNDATION, 2);
    freecell1.move(PileType.CASCADE, 36, 0, PileType.FOUNDATION, 2);
    freecell1.move(PileType.CASCADE, 37, 0, PileType.FOUNDATION, 2);
    freecell1.move(PileType.CASCADE, 38, 0, PileType.FOUNDATION, 2);

    freecell1.move(PileType.CASCADE, 39, 0, PileType.FOUNDATION, 3);
    freecell1.move(PileType.CASCADE, 40, 0, PileType.FOUNDATION, 3);
    freecell1.move(PileType.CASCADE, 41, 0, PileType.FOUNDATION, 3);
    freecell1.move(PileType.CASCADE, 42, 0, PileType.FOUNDATION, 3);
    freecell1.move(PileType.CASCADE, 43, 0, PileType.FOUNDATION, 3);
    freecell1.move(PileType.CASCADE, 44, 0, PileType.FOUNDATION, 3);
    freecell1.move(PileType.CASCADE, 45, 0, PileType.FOUNDATION, 3);
    freecell1.move(PileType.CASCADE, 46, 0, PileType.FOUNDATION, 3);
    freecell1.move(PileType.CASCADE, 47, 0, PileType.FOUNDATION, 3);
    freecell1.move(PileType.CASCADE, 48, 0, PileType.FOUNDATION, 3);
    freecell1.move(PileType.CASCADE, 49, 0, PileType.FOUNDATION, 3);
    freecell1.move(PileType.CASCADE, 50, 0, PileType.FOUNDATION, 3);
    freecell1.move(PileType.CASCADE, 51, 0, PileType.FOUNDATION, 3);

    assertEquals(true, freecell1.isGameOver());
  }

  // To test that if a game has not started, getGameState returns an empty string
  @Test
  public void testGetGameStateGameNotStarted() {
    assertEquals("", freecell1.getGameState());
  }

  // To test that if a game has started, getGameState returns the state of the game
  @Test
  public void testGameStateGameHasStarted() {
    freecell1.startGame(freecell1.getDeck(), 8, 4, false);
    String currGameState = "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n" +
            "C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n" +
            "C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n" +
            "C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n" +
            "C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n" +
            "C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n" +
            "C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n" +
            "C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠";

    assertEquals(currGameState, freecell1.getGameState());
  }
}
