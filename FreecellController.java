package cs3500.hw03;

import cs3500.hw02.FreecellOperations;
import cs3500.hw02.Card;
import cs3500.hw02.PileType;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;


/**
 * The controller for the game Freecell.
 */

public class FreecellController implements IFreecellController<Card> {

  private final Readable rd;
  private final Appendable ap;

  // To keep track of which piles have been found and to store the card values
  private PileType sourceType;
  private PileType destinationType;
  private Boolean sourcePileFound;
  private Integer clientSourcePile;
  private Boolean cardIndexFound;
  private Integer clientCardIndex;
  private Boolean destPileFound;
  private Integer clientDestPile;

  /**
   * Constructor for the FreecellController.
   *
   * @param rd Represents the readable used to take in client input
   * @param ap Represents the appendable used to transmit output to the client
   */

  public FreecellController(Readable rd, Appendable ap) {

    this.rd = rd;
    this.ap = ap;

    this.sourceType = null;
    this.destinationType = null;

    this.sourcePileFound = false;
    this.clientSourcePile = 0;

    this.cardIndexFound = false;
    this.clientCardIndex = 0;

    this.destPileFound = false;
    this.clientDestPile = 0;
  }

  /**
   * Transmits a message to the client.
   *
   * @param s message to be sent
   */

  private void transmitMessage(String s) {
    try {

      // Note : Added "\n" to the append in Assignment 4 for better clarity to user when
      // playing the game and reading the game state

      this.ap.append(s);
    } catch (IOException io) {
      return;
    }
  }

  /**
   * Determines if the Freecell controller has been properly initialized.
   *
   * @throws IllegalStateException if either of its Readable and Appendable objects have not been
   *                               initialized (i.e if they are null)
   */

  private void initializedReadableAndAppendable() {

    if (this.rd == null) {
      throw new IllegalStateException("Controller has not been initialized properly: invalid " +
              "Readable.");

    }

    if (this.ap == null) {
      throw new IllegalStateException("Controller has not been intiialized properly: invalid " +
              "Appendable.");
    }
  }

  /**
   * Determines if the Freecell controller has been passed a valid deck and model to play with.
   *
   * @param deck  the Freecell deck to be played with
   * @param model the Freecell model to be utilized for the game
   * @throws IllegalArgumentException if either the Freecell deck or model are null
   */

  private void validDeckAndModel(List<Card> deck, FreecellOperations<Card> model) {

    if (deck == null) {
      throw new IllegalArgumentException("Error: Null deck passed into the controller.");
    }

    if (model == null) {
      throw new IllegalArgumentException("Error: Null model passed into the controller.");
    }
  }

  /**
   * Asks the provided model to start a game with the provided parameters.
   *
   * @param deck        the Freecell deck to be played with
   * @param model       the Freecell model to be used for the game
   * @param numCascades the number of cascade piles
   * @param numOpens    the number of open piles
   * @param shuffle     the number of shuffle piles
   */

  private void askModelToBeginGame(List<Card> deck, FreecellOperations<Card> model, int numCascades,
                                   int numOpens, boolean shuffle) {
    try {
      model.startGame(deck, numCascades, numOpens, shuffle);
    } catch (IllegalArgumentException illegalArgument) {
      this.transmitMessage("Could not start game.");
      return;
    }
  }

  /**
   * Helps parse the client input to a pile type and a pile card index.
   *
   * @param currentInput the current input from the client
   * @param pileType     the type of pile the user inputs, either a source pile or destination pile
   */

  private void parsePile(String currentInput, String pileType) {

    Character firstChar = currentInput.charAt(0);

    switch (firstChar) {
      case 'C':

        try {

          if (pileType.equals("source pile")) {
            clientSourcePile =
                    Integer.parseInt(((currentInput.substring(1, currentInput.length()))))
                            - 1;
            sourcePileFound = true;
            sourceType = PileType.CASCADE;

          } else {
            clientDestPile =
                    Integer.parseInt(((currentInput.substring(1, currentInput.length()))))
                            - 1;
            destPileFound = true;
            destinationType = PileType.CASCADE;
          }

        } catch (NumberFormatException e) {
          this.transmitMessage(" Please input the " + pileType + " again. You must follow your " +
                  "cascade pile with a valid number.");
        }

        break;

      case 'F':

        try {

          if (pileType.equals("source pile")) {
            clientSourcePile =
                    Integer.parseInt(((currentInput.substring(1, currentInput.length()))))
                            - 1;
            sourcePileFound = true;
            sourceType = PileType.FOUNDATION;
          } else {
            clientDestPile =
                    Integer.parseInt(((currentInput.substring(1, currentInput.length()))))
                            - 1;
            destPileFound = true;
            destinationType = PileType.FOUNDATION;
          }

        } catch (NumberFormatException e) {
          this.transmitMessage(" Please input the " + pileType + " again. You must follow your " +
                  "foundation pile with a valid number.");
        }

        break;

      case 'O':

        try {

          if (pileType.equals("source pile")) {
            clientSourcePile =
                    Integer.parseInt(((currentInput.substring(1, currentInput.length()))))
                            - 1;
            sourcePileFound = true;
            sourceType = PileType.OPEN;

          } else {
            clientDestPile =
                    Integer.parseInt(((currentInput.substring(1, currentInput.length()))))
                            - 1;
            destPileFound = true;
            destinationType = PileType.OPEN;
          }

        } catch (NumberFormatException e) {
          this.transmitMessage(" Please input the " + pileType + " again. You must follow your " +
                  "open pile with a valid number.");
        }

        break;

      default:
        this.transmitMessage(" Please input the " + pileType + " again. You must name" +
                " a valid pile with 'C', 'O', or 'F'");
        break;
    }


  }

  /**
   * Helps parse the client's input into the card index to determine which card is to be moved.
   *
   * @param currentInput the current input from the client
   */

  private void parseCardIndex(String currentInput) {

    try {

      clientCardIndex = Integer.parseInt(currentInput) - 1;
      cardIndexFound = true;

    } catch (NumberFormatException e) {
      this.transmitMessage(" Please input the card index value again.");
    }
  }

  /**
   * Attempts to send a move to the model once all of the client's inputs have been properly
   * received.
   *
   * @param model the model of the Freecell game
   */

  private void tryToMove(FreecellOperations<Card> model) {

    try {
      // NOTE: Reinitialized the boolean flags before calling move so the controller is guaranteed
      // to take in a new set of inputs if the client puts in an invalid move in Assignment 4

      // Reinitialize the boolean flags for the next set of inputs from the client
      this.sourcePileFound = false;
      this.cardIndexFound = false;
      this.destPileFound = false;

      model.move(sourceType, clientSourcePile, clientCardIndex,
              destinationType, clientDestPile);

    } catch (IllegalArgumentException illegalArgument) {
      this.transmitMessage(" Invalid move. Try again. " + illegalArgument.getMessage());
    }
  }

  @Override
  public void playGame(List<Card> deck, FreecellOperations<Card> model, int numCascades,
                       int numOpens, boolean shuffle) {

    // Check to make sure that the readable and appendable have been initialized properly
    this.initializedReadableAndAppendable();

    // Check to make sure that the controller is given a valid deck and model
    this.validDeckAndModel(deck, model);

    // Ask the provided model to start a game with the provided parameters
    this.askModelToBeginGame(deck, model, numCascades, numOpens, shuffle);

    // To take in the client input
    Scanner scan = new Scanner(this.rd);

    while (scan.hasNext()) {

      String currentInput = scan.next();

      // Transmit game state to the appendable object as the model provides it
      this.transmitMessage(model.getGameState() + "\n");

      // Quitting the game
      if ((currentInput.equals("q") || currentInput.equals("Q"))) {

        this.transmitMessage("\n" + "Game quit prematurely.");
        return;
      }

      if (!this.sourcePileFound) {

        this.parsePile(currentInput, "source pile");

      } else if (this.sourcePileFound && !this.cardIndexFound) {

        this.parseCardIndex(currentInput);
      } else if (this.sourcePileFound && this.cardIndexFound && !this.destPileFound) {

        this.parsePile(currentInput, "destination pile");
      }

      // Check to see if we can send it to move
      if (this.sourcePileFound && this.cardIndexFound && this.destPileFound) {

        this.tryToMove(model);
      }
    }

    // Check to see if the game is over
    if (model.isGameOver()) {
      this.transmitMessage(model.getGameState() + "\n" + "Game over.");
      return;
    }

  }

}
