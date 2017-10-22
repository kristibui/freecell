package cs3500.hw04;

import java.util.ArrayList;

import cs3500.hw02.Card;
import cs3500.hw02.PileType;
import cs3500.hw02.FreecellModel;

/**
 * Model of a multi-card-move implementation of {@Link FreecellOperations} for the game Freecell.
 * This model contains all of the basic operations utilized in Freecell and supports a version of
 * the game where a player can move multiple cards.
 */

public class FreecellMultiMoveModel extends FreecellModel {

  /**
   * Constructs a multi-move Freecell model.
   */

  public FreecellMultiMoveModel() {

    super();
  }

  /**
   * Determines if the multi-move build is valid.
   *
   * @param currentCascPile the current cascade pile in which the user will move cards from
   * @param cardIndex       the index of the first card of the move the user wants to move
   * @param lastCasDestCard the last card in the destination cascade pile
   */

  private void validBuild(ArrayList<Card> currentCascPile, int cardIndex, Card lastCasDestCard) {

    // To keep track of the cards to compare
    Card currentCard;
    Card adjacentCard;

    // First: Checking the build of the multiple cards to be moved
    for (int i = cardIndex - 1; i < currentCascPile.size() - 2; i++) {

      // Check to see that it forms a proper build with the destination card
      if (i == (cardIndex - 1)) {

        currentCard = lastCasDestCard;
        adjacentCard = currentCascPile.get(cardIndex);
      } else {

        currentCard = currentCascPile.get(i);
        adjacentCard = currentCascPile.get(i + 1);
      }

      // Check that the colors are alternating
      if ((((currentCard.getSuit().equals("diamond"))
              || (currentCard.getSuit().equals("heart")))
              && ((adjacentCard.getSuit().equals("diamond"))
              || (adjacentCard.getSuit().equals("heart"))))
              || (((currentCard.getSuit().equals("club"))
              || (currentCard.getSuit().equals("spade")))
              && ((adjacentCard.getSuit().equals("club"))
              || (adjacentCard.getSuit().equals("spade"))))) {

        throw new IllegalArgumentException("Incorrect Build: Colors must alternate.");
      }

      // Check that the numbers are descending
      else if (currentCard.getInt() - adjacentCard.getInt() != 1) {

        throw new IllegalArgumentException("Incorrect Build: Values must be descending.");
      }
    }
  }

  /**
   * Determines if the number of cards to be moved exceeds the maximum number of cards that can be
   * moved.
   *
   * @param numCards the number of cards in a cascade pile the user is trying to move
   */

  private void intermediateSlotCheck(int numCards) {

    int numFreeOpenPiles = 0;
    int numFreeCascadePiles = 0;

    // Determining how many empty open piles there are
    for (int i = 0; i < openPile.size(); i++) {

      if (openPile.get(i).isEmpty()) {
        numFreeOpenPiles++;
      }

    }

    // Determining how many empty cascade piles there are
    for (int i = 0; i < cascadePiles.size(); i++) {

      if (cascadePiles.get(i).isEmpty()) {
        numFreeCascadePiles++;
      }
    }

    double maxNumMovingCards = ((numFreeOpenPiles + 1) * Math.pow(2, numFreeCascadePiles));

    if (numCards > maxNumMovingCards) {

      throw new IllegalArgumentException("Impossible move: Too many cards to be moved.");
    }
  }

  /**
   * Helps move the multiple cards from one cascade pile to another.
   *
   * @param cardIndex  the starting card in the multiple cards to be moved
   * @param pileNumber the index of the source cascade pile
   */

  private void moveMultipleCards(int cardIndex, int pileNumber, int destPileNumber) {

    // Retrieve the multiple cards that the user is moving
    for (int i = cardIndex; i < cascadePiles.get(pileNumber).size(); i++) {

      // Add to new cascade pile
      Card currentCard = cascadePiles.get(pileNumber).get(i);
      cascadePiles.get(destPileNumber).add(currentCard);

      // Remove from original cascade pile
      cascadePiles.get(pileNumber).remove(i);

      // To keep track of the index once the card is removed from the original pile
      i -= 1;
    }

  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
                   int destPileNumber) throws IllegalArgumentException {

    // Check to see that the user is inputting a multi-move, where moving from and to a cascade pile
    // and more than one card is being moved
    if (source == PileType.CASCADE && destination == PileType.CASCADE) {

      // Check to make sure that it is a valid pile
      if (pileNumber >= numCascPiles || cardIndex < 0 || destPileNumber >= numCascPiles) {
        throw new IllegalArgumentException("Invalid parameters given.");
      }

      // Check to see that the moving pile is a valid build and forms a valid build with the last
      // card of the destination cascade pile
      this.validBuild(cascadePiles.get(pileNumber), cardIndex,
              cascadePiles.get(destPileNumber).get(cascadePiles.get(destPileNumber).size() - 1));

      // Check to see that the number of moving cards does not exceed the maximum number of
      // cards that can be moved
      this.intermediateSlotCheck(cascadePiles.get(pileNumber).size() - cardIndex);

      // Retrieve the multiple cards that the user is moving and move to the destination
      // cascade pile
      this.moveMultipleCards(cardIndex, pileNumber, destPileNumber);

    } else {

      // If the user is not inputting a multi-move, then do a single move
      super.move(source, pileNumber, cardIndex, destination, destPileNumber);
    }
  }

}
