package cs3500.freecell.controller;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.view.FreecellTextView;
import cs3500.freecell.view.FreecellView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the controller for a freecell solitaire game that takes input from the user and
 * outputs the correct data after sending the user data to the model and view.
 */
public class SimpleFreecellController implements FreecellController<Card> {

  private final FreecellModel<Card> model;
  private final FreecellView view;
  private final Readable in;

  /**
   * Constructs a SimpleFreecellController and a FreecellTextView to be stored in the controller.
   *
   * @param model the FreecellModel to be used
   * @param rd    the Readable object to be used
   * @param ap    the Appendable object to be used
   * @throws IllegalArgumentException if any of the inputs are null
   */
  public SimpleFreecellController(FreecellModel<Card> model, Readable rd, Appendable ap)
      throws IllegalArgumentException {
    if (model == null || rd == null || ap == null) {
      throw new IllegalArgumentException("invalid parameters");
    }
    this.model = model;
    this.view = new FreecellTextView(model, ap);
    this.in = rd;
  }


  @Override
  public void playGame(List<Card> deck, int numCascades, int numOpens, boolean shuffle)
      throws IllegalStateException, IllegalArgumentException {
    if (deck == null) {
      throw new IllegalArgumentException("Invalid deck");
    }

    try {
      model.startGame(deck, numCascades, numOpens, shuffle);
    } catch (IllegalArgumentException e) {
      this.renderController("Could not start game.", false);
      return;
    }

    this.playSequence();
  }

  /**
   * Loops through the sequence of displaying the board, taking input from the user, and making a
   * move with those inputs until the game is won (game is over) or the user quits.
   *
   * @throws IllegalStateException if writing to the Appendable object used by it fails or reading
   *                               from the provided Readable fails
   */
  private void playSequence() throws IllegalStateException {
    String input1;
    String input2;
    String input3;
    PileType source;
    int pileIndex;
    int cardIndex;
    PileType destination;
    int destPileNumber;
    Scanner scanner = new Scanner(in);

    this.renderController("\n", true);

    while (!model.isGameOver()) {
      while (true) {
        if (scanner.hasNext()) {
          input1 = scanner.next();
        } else {
          throw new IllegalStateException();
        }
        if (input1.equalsIgnoreCase("Q")) {
          this.renderController("Game quit prematurely.", false);
          return;
        }
        if (this.isPile(input1)) {
          source = this.getPileType(input1);
          pileIndex = Integer.parseInt(input1.substring(1));
          break;
        } else {
          this.renderController("Please enter a valid source.\n", false);
        }
      }
      while (true) {
        if (scanner.hasNext()) {
          input2 = scanner.next();
        } else {
          throw new IllegalStateException();
        }
        if (input2.equalsIgnoreCase("Q")) {
          this.renderController("Game quit prematurely.", false);
          return;
        }
        if (this.isNumber(input2)) {
          cardIndex = Integer.parseInt(input2);
          break;
        } else {
          this.renderController("Please enter a valid card index.\n", false);
        }
      }
      while (true) {
        if (scanner.hasNext()) {
          input3 = scanner.next();
        } else {
          throw new IllegalStateException();
        }
        if (input3.equalsIgnoreCase("Q")) {
          this.renderController("Game quit prematurely.", false);
          return;
        }
        if (this.isPile(input3)) {
          destination = this.getPileType(input3);
          destPileNumber = Integer.parseInt(input3.substring(1));
          break;
        } else {
          this.renderController("Please enter a valid destination.\n", false);
        }
      }
      try {
        model.move(source, pileIndex - 1, cardIndex - 1,
            destination, destPileNumber - 1);
      } catch (IllegalArgumentException e) {
        this.renderController("Invalid move. Try again. Could not move card number "
            + cardIndex + " from " + source + " pile number " + pileIndex + " to " + destination
            + " pile number " + destPileNumber + "\n", false);
        continue;
      }
      this.renderController("\n", true);
    }
    this.renderController("Game over.", false);
  }

  /**
   * Gets the PileType associated with the String given as input.
   *
   * @param input the String used to determine if it denotes a certain PileType
   * @return the PileType associated with the input, if valid
   * @throws IllegalArgumentException if a PileType can't be found or input is null
   */
  private PileType getPileType(String input) throws IllegalArgumentException {
    if (input == null) {
      throw new IllegalArgumentException("Null input");
    }
    char type = input.toUpperCase().charAt(0);
    switch (type) {
      case 'C':
        return PileType.CASCADE;
      case 'O':
        return PileType.OPEN;
      case 'F':
        return PileType.FOUNDATION;
      default:
        throw new IllegalArgumentException("Invalid input");
    }
  }

  /**
   * method that contains all rendering in the controller, asks the view to render.
   *
   * @param message the message to be rendered by the view
   * @param board   if true, renders the board as well
   * @throws IllegalStateException    if the Appendable fails
   * @throws IllegalArgumentException if the message is null
   */
  private void renderController(String message, boolean board)
      throws IllegalStateException, IllegalArgumentException {
    if (message == null) {
      throw new IllegalArgumentException("Null message");
    }
    try {
      if (board) {
        view.renderBoard();
      }
      view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  /**
   * Determines if the given input contains a valid PileType identifier as the first character.
   *
   * @param input the String being analyzed to see if the first character is a PileType identifier
   * @return true if the first character of the String is one of 3 PileType identifiers
   * @throws IllegalArgumentException if the input is null
   */
  private boolean isPile(String input) throws IllegalArgumentException {
    if (input == null) {
      throw new IllegalArgumentException("Null input");
    }
    if (input.equals("")) {
      return false;
    }
    List<Character> valid = new ArrayList<>(Arrays.asList('C', 'O', 'F'));
    return valid.contains(input.toUpperCase().charAt(0)) && this.isNumber(input.substring(1));
  }

  /**
   * Determines if the given String can be converted into a valid int.
   *
   * @param input the String being analyzed if it can be parsed to an int
   * @return true if the String can be an int, false otherwise
   * @throws IllegalArgumentException if the input is null
   */
  private boolean isNumber(String input) throws IllegalArgumentException {
    if (input == null) {
      throw new IllegalArgumentException("Null input");
    }
    try {
      Integer.parseInt(input);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
