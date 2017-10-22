package cs3500.hw02;

/**
 * Represents a single card in a standard deck of 52 cards.
 */

public class Card {

  private final int cardValue;
  private final String suit;

  /**
   * Constructs a card.
   *
   * @param cardValue the value of the card, ranging from ace to king
   * @param suit      the suit of the card: either club, diamond, heart or spade
   */

  public Card(int cardValue, String suit) {

    if (cardValue > 13 || cardValue < 1) {
      throw new IllegalArgumentException("Card value is out of range.");
    } else {
      this.cardValue = cardValue;
    }
    if (!(suit.equals("club") || suit.equals("diamond")
            || suit.equals("heart") || suit.equals("spade"))) {
      throw new IllegalArgumentException("Illegal card suit.");
    } else {
      this.suit = suit;
    }

  }

  /**
   * Retrieves the type of suit of the card.
   *
   * @return the suit of the card
   */

  public String getSuit() {
    return this.suit;
  }

  /**
   * Retrieves the value of the card.
   *
   * @return the value of the card
   */

  public int getInt() {
    return this.cardValue;
  }

  /**
   * Prints the value and suit of the card.
   *
   * @return the value and suit of the card
   */

  public String toString() {

    String finalString = "";

    if (cardValue == 1) {
      finalString = "A";
    }

    else if (cardValue == 11) {
      finalString = "J";
    }

    else if (cardValue == 12) {
      finalString = "Q";
    }

    else if (cardValue == 13) {
      finalString = "K";
    } else {
      finalString = Integer.toString(cardValue);
    }

    if (this.suit.equals("club")) {
      finalString = finalString + "♣";
    } else if (this.suit.equals("diamond")) {
      finalString = finalString + "♦";
    } else if (this.suit.equals("heart")) {
      finalString = finalString + "♥";
    } else {
      finalString = finalString + "♠";
    }

    return finalString;
  }

}
