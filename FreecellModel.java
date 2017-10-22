package cs3500.hw02;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

// Note: Updated the Javadoc of the Freecell Model in Assignment 4

/**
 * Model of a single-move implementation of {@link FreecellOperations} for the game Freecell. This
 * model contains all of the basic operations utilized in Freecell and supports a version of the
 * game where a player can move only a single card at a time.
 */

public class FreecellModel implements FreecellOperations<Card> {

  // NOTE: changed visibility of the fields from private to protected, so that the
  // FreecellMultiMoveModel class has access in Assignment 4

  // To represent the foundation pile cards
  protected ArrayList<ArrayList<Card>> foundationPiles;

  // To represent the cascade piles
  protected ArrayList<ArrayList<Card>> cascadePiles;

  // To represent the open piles
  protected ArrayList<ArrayList<Card>> openPile;

  // To represent if the game has started
  protected Boolean hasGameStarted;

  // To represent the number of open piles
  protected int numOpPiles;

  // To represent the number of cascade piles
  protected int numCascPiles;

  /**
   * Constructs a new FreecellModel. Note that constructing a new FreecellModel does not start the
   * game.
   */

  public FreecellModel() {

    cascadePiles = new ArrayList<ArrayList<Card>>();
    openPile = new ArrayList<ArrayList<Card>>();
    foundationPiles = new ArrayList<ArrayList<Card>>(4);
    hasGameStarted = false;

    for (int i = 0; i < 4; i++) {
      ArrayList<Card> foundPile = new ArrayList<Card>();
      foundationPiles.add(foundPile);

    }
  }

  @Override
  public List<Card> getDeck() {

    // Final list to be created
    List<Card> finalList = new ArrayList<Card>(52);

    // New card to be added to the list
    Card newCard;

    // 1. Creating all of the club cards
    for (int i = 1; i <= 13; i++) {

      newCard = new Card(i, "club");
      finalList.add(newCard);
    }

    // 2. Creating all of the diamond cards
    for (int i = 1; i <= 13; i++) {

      newCard = new Card(i, "diamond");
      finalList.add(newCard);
    }

    // 3. Creating all of the heart cards
    for (int i = 1; i <= 13; i++) {

      newCard = new Card(i, "heart");
      finalList.add(newCard);
    }

    // 4. Creating all of the spade cards
    for (int i = 1; i <= 13; i++) {

      newCard = new Card(i, "spade");
      finalList.add(newCard);
    }

    return finalList;
  }

  /**
   * Determines if the parameters given when starting the game are valid.
   *
   * @param deck            the given deck to be used for the game
   * @param numCascadePiles the number of cascade piles picked for the game
   * @param numOpenPiles    the number of open piles picked for the game
   */

  private void invalidDeckinvalidPiles(List<Card> deck, int numCascadePiles, int numOpenPiles) {

    List<Card> validDeck = this.getDeck();
    HashSet<String> hashSetCards = new HashSet<String>(52);

    if (numCascadePiles < 4 || numOpenPiles < 1) {
      throw new IllegalArgumentException("Incorrect number of piles.");
    }

    if ((deck.size() != validDeck.size())) {
      throw new IllegalArgumentException("Invalid size of deck.");
    }

    for (int i = 0; i < deck.size(); i++) {

      if (deck.get(i) == null) {
        throw new IllegalArgumentException("Null card in deck.");
      }

      if (!(hashSetCards.add(deck.get(i).toString()))) {
        throw new IllegalArgumentException("Duplicate found in deck.");
      }
    }
  }

  /**
   * Initializes the number of cascade and open piles based on the values given in startGame.
   *
   * @param numCascadePiles the number of cascade piles to be made
   * @param numOpenPiles    the number of open piles to be made
   */

  private void initializePiles(int numCascadePiles, int numOpenPiles) {

    for (int i = 0; i < numCascadePiles; i++) {
      ArrayList<Card> cascPile = new ArrayList<Card>();
      this.cascadePiles.add(cascPile);
    }

    for (int i = 0; i < numOpenPiles; i++) {
      ArrayList<Card> opPile = new ArrayList<Card>(1);
      this.openPile.add(opPile);

    }
  }

  /**
   * Deals the deck to the cascade piles in round robin fashion.
   *
   * @param deck            the deck to be dealt to the cascade piles
   * @param numCascadePiles the number of cascade piles
   */

  private void roundRobinDeal(List<Card> deck, int numCascadePiles) {

    for (int i = 0; i < numCascadePiles; i++) {
      for (int j = i; j < deck.size(); j += numCascadePiles) {
        this.cascadePiles.get(i).add(deck.get(j));
      }
    }
  }

  @Override
  public void startGame(List<Card> deck, int numCascadePiles, int numOpenPiles, boolean shuffle) {

    // Check to see that the given parameters are valid
    this.invalidDeckinvalidPiles(deck, numCascadePiles, numOpenPiles);

    // If a game has already been started - need to reset values again
    if (hasGameStarted) {

      cascadePiles = new ArrayList<ArrayList<Card>>();
      openPile = new ArrayList<ArrayList<Card>>();
      foundationPiles = new ArrayList<ArrayList<Card>>(4);
      hasGameStarted = false;

      for (int i = 0; i < 4; i++) {
        ArrayList<Card> foundPile = new ArrayList<Card>();
        foundationPiles.add(foundPile);

      }
    }

    this.numOpPiles = numOpenPiles;
    this.numCascPiles = numCascadePiles;
    this.hasGameStarted = true;

    // Initializing the amount of cascade and open piles
    this.initializePiles(numCascadePiles, numOpenPiles);

    // Shuffle the deck
    if (shuffle) {

      Collections.shuffle(deck);
    }

    // Dealing to the cascade piles
    this.roundRobinDeal(deck, numCascadePiles);

  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
                   int destPileNumber) throws IllegalArgumentException {

    Card movingCard = this.determineMovingCard(source, pileNumber, cardIndex, destPileNumber);

    this.determineDestinationOfCard(source, pileNumber, cardIndex, destination,
            destPileNumber, movingCard);
  }

  /**
   * Determines if the card to be moved can be legally moved.
   *
   * @param source         the type of the source pile
   * @param pileNumber     the pile number of the given type, starting at 0
   * @param cardIndex      the index of the card to be moved from the source pile, starting at 0
   * @param destPileNumber the pile number of the given type
   * @return the card to be moved
   * @throws IllegalArgumentException if any of the parameters are invalid
   */

  private Card determineMovingCard(PileType source, int pileNumber,
                                   int cardIndex, int destPileNumber) {

    Card movingCard = null;

    if (pileNumber < 0 || cardIndex < 0 || destPileNumber < 0) {
      throw new IllegalArgumentException("Invalid parameters given.");
    }

    switch (source) {
      case CASCADE:

        if (pileNumber >= this.numCascPiles) {

          throw new IllegalArgumentException("Impossible move: Pile does not exist.");
        } else if (cascadePiles.get(pileNumber).size() <= cardIndex) {

          throw new IllegalArgumentException("Impossible move: Card index out of bounds.");
        } else if (cardIndex < cascadePiles.get(pileNumber).size() - 1) {

          throw new IllegalArgumentException("Impossible move: Card index out of bounds.");
        } else {

          ArrayList<Card> pileToTakeFrom = cascadePiles.get(pileNumber);
          movingCard = pileToTakeFrom.get(pileToTakeFrom.size() - 1);
          return movingCard;
        }

      case OPEN:

        if (this.numOpPiles < pileNumber) {

          throw new IllegalArgumentException("Impossible move: Pile does not exist.");
        } else if (this.openPile.get(pileNumber).isEmpty()) {

          throw new IllegalArgumentException("Impossible move: Cannot take from an empty" +
                  " open pile.");
        } else if (cardIndex != 0) {
          throw new IllegalArgumentException("Impossible move: Card must be " +
                  "located at the 0 index for open piles.");
        } else {

          ArrayList<Card> pileToTakeFrom = openPile.get(pileNumber);
          movingCard = pileToTakeFrom.get(pileToTakeFrom.size() - 1);
          return movingCard;
        }

      case FOUNDATION:
        throw new IllegalArgumentException("Impossible move: Cannot take from a foundation" +
                " pile.");

      default:
        break;
    }

    return movingCard;
  }

  /**
   * Determines if a card is capable of moving to a destination and if so, moves it.
   *
   * @param source         the source pile
   * @param pileNumber     the pile in which a card is chosen from
   * @param cardIndex      the index of the moving card
   * @param destination    the PileType the card is moving to
   * @param destPileNumber the pile in which a card is moving to
   * @param movingCard     the card to be moved
   */

  private void determineDestinationOfCard(PileType source, int pileNumber,
                                          int cardIndex, PileType destination,
                                          int destPileNumber, Card movingCard) {
    switch (destination) {

      case CASCADE:

        if (destPileNumber >= this.numCascPiles) {
          throw new IllegalArgumentException("Impossible move: Pile does not exist.");
        } else if (!(cascadePiles.get(destPileNumber).isEmpty())) {

          Card lastCascadeCard = cascadePiles.get(
                  destPileNumber).get(cascadePiles.get(destPileNumber).size() - 1);

          // Check that the colors are opposite
          if ((((lastCascadeCard.getSuit().equals("diamond"))
                  || (lastCascadeCard.getSuit().equals("heart")))
                  && ((movingCard.getSuit().equals("diamond"))
                  || (movingCard.getSuit().equals("heart"))))
                  || (((lastCascadeCard.getSuit().equals("club"))
                  || (lastCascadeCard.getSuit().equals("spade")))
                  && ((movingCard.getSuit().equals("club"))
                  || (movingCard.getSuit().equals("spade"))))) {

            throw new IllegalArgumentException("Impossible move: Card colors must alternate.");

          } else if ((lastCascadeCard.getInt() - movingCard.getInt()) != 1) {

            throw new IllegalArgumentException("Impossible move: Card must be one value lower " +
                    "than the last current card in the cascade pile.");
          } else {

            if (source == PileType.CASCADE) {

              cascadePiles.get(destPileNumber).add(movingCard);
              cascadePiles.get(pileNumber).remove(movingCard);
            }

            if (source == PileType.OPEN) {

              cascadePiles.get(destPileNumber).add(movingCard);
              openPile.get(pileNumber).remove(movingCard);
            }
          }

        } else {

          if (source == PileType.CASCADE) {

            cascadePiles.get(destPileNumber).add(movingCard);
            cascadePiles.get(pileNumber).remove(movingCard);
          }

          if (source == PileType.OPEN) {

            cascadePiles.get(destPileNumber).add(movingCard);
            openPile.get(pileNumber).remove(movingCard);
          }
        }
        break;

      case FOUNDATION:

        if (destPileNumber >= 4) {
          throw new IllegalArgumentException("Impossible move: Foundation pile does not exist.");
        }

        if (foundationPiles.get(destPileNumber).isEmpty()) {
          if (movingCard.getInt() == 1) {

            if (source == PileType.CASCADE) {

              foundationPiles.get(destPileNumber).add(movingCard);
              cascadePiles.get(pileNumber).remove(movingCard);
            }

            if (source == PileType.OPEN) {

              foundationPiles.get(destPileNumber).add(movingCard);
              openPile.get(pileNumber).remove(movingCard);
            }

          } else {
            throw new IllegalArgumentException("Impossible move: Starting card of a " +
                    "foundation pile must be an ace.");
          }
        } else {

          Card lastFoundationPileCard = foundationPiles.get(
                  destPileNumber).get(foundationPiles.get(destPileNumber).size() - 1);

          if (!(lastFoundationPileCard.getSuit().equals(movingCard.getSuit()))) {
            throw new IllegalArgumentException("Impossible move: Cards added to a" +
                    " foundation pile must be of the same suit.");
          } else if ((movingCard.getInt() - lastFoundationPileCard.getInt()) != 1) {
            throw new IllegalArgumentException("Impossible move: Cards added to a" +
                    " foundation pile must be one higher than the last card.");
          } else {

            if (source == PileType.CASCADE) {

              foundationPiles.get(destPileNumber).add(movingCard);
              cascadePiles.get(pileNumber).remove(movingCard);
            }

            if (source == PileType.OPEN) {

              foundationPiles.get(destPileNumber).add(movingCard);
              openPile.get(pileNumber).remove(movingCard);

            }
          }
        }
        break;

      case OPEN:

        if (destPileNumber >= this.numOpPiles) {
          throw new IllegalArgumentException("Impossible move: Open pile does not exist.");
        }

        if (openPile.get(destPileNumber).isEmpty()) {

          if (source == PileType.CASCADE) {

            openPile.get(destPileNumber).add(movingCard);
            cascadePiles.get(pileNumber).remove(movingCard);
          }

          if (source == PileType.OPEN) {

            openPile.get(destPileNumber).add(movingCard);
            openPile.get(pileNumber).remove(movingCard);
          }
        } else {
          throw new IllegalArgumentException("Impossible move: Open pile must be empty" +
                  " in order to put a card there.");
        }
        break;

      default:
        throw new IllegalArgumentException("Impossible move!");
    }
  }

  @Override
  public boolean isGameOver() {

    int finalCount = 0;

    for (int i = 0; i < foundationPiles.size(); i++) {
      finalCount = finalCount + foundationPiles.get(i).size();
    }

    return finalCount == 52;
  }

  @Override
  public String getGameState() {

    // If a game has not started: return an empty string
    if (!(hasGameStarted)) {
      return "";
    }

    String currGameState = "";

    // Producing the foundation piles string
    for (int i = 0; i < 4; i++) {

      int cardNum = i + 1;

      currGameState = currGameState + "F" + cardNum + ":";
      int currLength = foundationPiles.get(i).size();

      for (int j = 0; j < currLength; j++) {

        if (j == currLength - 1) {

          currGameState = currGameState + " " + foundationPiles.get(i).get(j).toString();
        } else {

          currGameState = currGameState + " " + foundationPiles.get(i).get(j).toString() + ",";
        }
      }

      currGameState = currGameState + "\n";
    }

    // Producing the open piles string
    for (int i = 0; i < numOpPiles; i++) {

      int cardNum = i + 1;

      currGameState = currGameState + "O" + cardNum + ":";
      int currLength = openPile.get(i).size();
      for (int j = 0; j < currLength; j++) {

        if (j == currLength - 1) {

          currGameState = currGameState + " " + openPile.get(i).get(j).toString();
        } else {
          currGameState = currGameState + " " + openPile.get(i).get(j).toString() + ",";
        }
      }

      currGameState = currGameState + "\n";
    }

    // Producing the cascade piles string
    for (int i = 0; i < numCascPiles; i++) {

      int cardNum = i + 1;

      currGameState = currGameState + "C" + cardNum + ":";
      int currLength = cascadePiles.get(i).size();

      for (int j = 0; j < currLength; j++) {

        if (j == currLength - 1) {
          currGameState = currGameState + " " + cascadePiles.get(i).get(j).toString();
        } else {

          currGameState = currGameState + " " + cascadePiles.get(i).get(j).toString() + ",";
        }
      }

      if (i == numCascPiles - 1) {
        continue;
      } else {

        currGameState = currGameState + "\n";
      }
    }

    return currGameState;
  }
}
