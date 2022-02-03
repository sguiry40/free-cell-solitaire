package cs3500.freecell.model.hw04;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.Card;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
Design change:
I chose to have the SimpleFreecellModel class and the new MultimoveFreecellModel that was
required to create extend a single abstract class titles AbstractFreecellModel. This new class
implements FreecellModel<Card> and has all the same implementations as SimpleFreecellModel,
except for the abstract move method. I also changed any fields I had that weren't private (and
could be private) to private. I also redesigned my model tests, controller tests, and view tests
so that the model they operate on creates an instance of both SimpleFreecellModel and
MultimoveFreecellModel.
 */

/**
 * Represents the model for a freecell solitaire game that supports both single moves and
 * multimoves.
 */
public abstract class AbstractFreecellModel implements FreecellModel<Card> {

  private List<List<Card>> cascadePiles;
  private List<List<Card>> openPiles;
  private List<List<Card>> foundationPiles;
  private boolean gameStarted;
  private Random rand;

  /**
   * Default constructor for freecell model with no parameters.
   */
  public AbstractFreecellModel() {
    rand = new Random();
    cascadePiles = new ArrayList<>();
    openPiles = new ArrayList<>();
    foundationPiles = new ArrayList<>();
    gameStarted = false;
  }

  /**
   * Constructor for freecell model that takes a Random (for testing shuffle purposes).
   *
   * @param rand the Random passed (likely with seed 1) to be assigned to the rand field
   */
  public AbstractFreecellModel(Random rand) {
    this();
    if (rand == null) {
      throw new IllegalArgumentException("Invalid parameter");
    }
    this.rand = rand;
  }

  @Override
  public List<Card> getDeck() {
    List<Card> deck = new ArrayList<>();
    List<String> suits = new ArrayList<>(Arrays.asList("♠", "♣", "♥", "♦"));
    int loop = 0;
    while (loop < 4) {
      for (int i = 0; i < 13; i++) {
        deck.add(new Card(i + 1, suits.get(0)));
      }
      loop++;
      suits.remove(0);
    }
    return deck;
  }

  @Override
  public void startGame(List<Card> deck, int numCascadePiles, int numOpenPiles, boolean shuffle)
      throws IllegalArgumentException {
    if (deck == null || deck.size() != 52 || this.invalidDeck(deck)) {
      throw new IllegalArgumentException("Invalid deck");
    } else if (numCascadePiles < 4 || numCascadePiles > 52
        || numOpenPiles < 1 || numOpenPiles > 52) {
      throw new IllegalArgumentException("Invalid parameters");
    }

    this.gameStarted = true;

    // initialize openPiles
    this.openPiles = new ArrayList<>();
    int index = 0;
    while (index < numOpenPiles) {
      this.openPiles.add(new ArrayList<>());
      index++;
    }

    // initialize foundationPiles
    this.foundationPiles = new ArrayList<>();
    int index2 = 0;
    while (index2 < 4) {
      foundationPiles.add(new ArrayList<>());
      index2++;
    }

    // initialize cascadePiles
    List<Card> deckShuffle = new ArrayList<>(deck);
    Collections.shuffle(deckShuffle, this.rand);
    if (shuffle) {
      this.cascadePiles = this.deal(deckShuffle, numCascadePiles);
    } else {
      this.cascadePiles = this.deal(deck, numCascadePiles);
    }
  }

  /**
   * Checks if the given deck has duplicate cards or cards with unknown suits or invalid values.
   *
   * @param deck the given deck to inspect
   * @return true if invalid, false if valid
   */
  private boolean invalidDeck(List<Card> deck) {
    List<String> suits = new ArrayList<>(Arrays.asList("♠", "♣", "♥", "♦"));
    for (int i = 0; i < deck.size(); i++) {
      if (!suits.contains(deck.get(i).getSuit())) {
        return true;
      }
      for (int j = i + 1; j < deck.size(); j++) {
        if (deck.get(i).equals(deck.get(j))) {
          return true;
        }
      }
    }
    return false;
  }


  /**
   * Deals the given deck into the amount of given cascade piles desired.
   *
   * @param deck            the List of Cards to be arranged into cascade piles
   * @param numCascadePiles the number of cascade piles the cards will be arranged into
   * @return the cascade piles as a List of List
   */
  private List<List<Card>> deal(List<Card> deck, int numCascadePiles) {
    List<List<Card>> result = new ArrayList<>();
    int index = 0;
    while (index < numCascadePiles) {
      result.add(new ArrayList<>());
      index++;
    }
    int i = 0;
    for (Card card : deck) {
      result.get(i).add(card);
      if (i == numCascadePiles - 1) {
        i = 0;
      } else {
        i++;
      }
    }
    return result;
  }

  public abstract void move(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) throws IllegalArgumentException, IllegalStateException;

  /**
   * Performs a single card move from the specified pile type, pile number, and card number
   * specified to the destination and destination pile number, if the move is valid.
   *
   * @param source         the type of the source pile see @link{PileType}
   * @param pileNumber     the pile number of the given type, starting at 0
   * @param cardIndex      the index of the card to be moved from the source pile, starting at 0
   * @param destination    the type of the destination pile (see
   * @param destPileNumber the pile number of the given type, starting at 0
   * @throws IllegalArgumentException if the move is invalid
   */
  protected void singleMove(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) throws IllegalArgumentException {
    if (source == PileType.OPEN) {
      if (this.validSingleMove(destination, destPileNumber, this.getOpenCardAt(pileNumber))) {
        this.moveTo(source, pileNumber, cardIndex, destination, destPileNumber);
      } else {
        throw new IllegalArgumentException(
            "Invalid move: not a valid move from OPEN to " + destination);
      }
    } else if (source == PileType.CASCADE) {
      if (this.validSingleMove(destination, destPileNumber,
          this.getCascadeCardAt(pileNumber, cardIndex))) {
        this.moveTo(source, pileNumber, cardIndex, destination, destPileNumber);
      } else {
        throw new IllegalArgumentException(
            "Invalid move: not a valid move from CASCADE to " + destination);
      }
    } else {
      throw new IllegalArgumentException(
          "Invalid move: not a valid move from " + source + " to " + destination);
    }
  }

  /**
   * Checks to make sure that none of the objects passed as parameters are null, that the game has
   * started, and that all the index parameters actually exist.
   *
   * @param source         the type of the source pile see @link{PileType}
   * @param pileNumber     the pile number of the given type, starting at 0
   * @param cardIndex      the index of the card to be moved from the source pile, starting at 0
   * @param destination    the type of the destination pile (see
   * @param destPileNumber the pile number of the given type, starting at 0
   * @throws IllegalArgumentException if any of th object parameters are null or any of the indices
   *                                  do not exist
   * @throws IllegalStateException    if the game has not yet started
   */
  protected void checkParams(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) throws IllegalArgumentException, IllegalStateException {
    this.checkGameStartedException();
    if (source == null || destination == null) {
      throw new IllegalArgumentException("Cannot pass null parameters");
    }
    this.checkIndex(pileNumber, this.checkHowManyPiles(source));
    this.checkIndex(cardIndex, this.pileSize(source, pileNumber));
    this.checkIndex(destPileNumber, this.checkHowManyPiles(destination));
  }

  /**
   * Throws an exception if the gameStarted boolean is false, is only made true in startGame.
   *
   * @throws IllegalStateException if the game has not started
   */
  protected void checkGameStartedException() throws IllegalStateException {
    if (!gameStarted) {
      throw new IllegalStateException("Game not started");
    }
  }

  /**
   * Returns true if the move the user is attempting is a valid move. Includes the rules for moving
   * to any of the three pile types. Rules include: User can only move to open when the destination
   * is empty. User can only move to cascade when the last card in the destination is one less and
   * different color than the card being moved or the cascade pile is empty. User can only move to
   * foundation when the destination is empty and the source is an ace or the last card in the
   * destination is one less and same suit as the card being moved.
   *
   * @param destination    the type of pile that the user is trying to move to
   * @param destPileNumber the index of the pile that the user is trying to move to
   * @param card           the card that the use is trying to move
   * @return true if the move is valid, false otherwise
   */
  private boolean validSingleMove(PileType destination, int destPileNumber, Card card) {
    switch (destination) {
      case OPEN:
        return this.getNumCardsInOpenPile(destPileNumber) == 0;

      case CASCADE:
        return this.getNumCardsInCascadePile(destPileNumber) == 0
            ||
            this.getCascadeCardAt(destPileNumber,
                this.getNumCardsInCascadePile(destPileNumber) - 1)
                .oneMoreDiffCol(card);

      case FOUNDATION:
        return (this.getNumCardsInFoundationPile(destPileNumber) == 0
            && card.isAce())
            || this.getFoundationCardAt(destPileNumber,
            this.getNumCardsInFoundationPile(destPileNumber) - 1)
            .oneLessSameSuit(card);

      default:
        return false;
    }
  }

  /**
   * Removes the card being moved from the source pile and adds it to the destination pile.
   *
   * @param source         the type of the pile to remove from
   * @param pileNumber     the number of which pile to remove from
   * @param cardIndex      the card index to remove
   * @param destination    the type of the pile to add to
   * @param destPileNumber the number of which pile to add to
   */
  protected void moveTo(PileType source, int pileNumber, int cardIndex,
      PileType destination, int destPileNumber) {
    switch (destination) {
      case OPEN:
        this.openPiles.get(destPileNumber).add(this.moveFrom(source, pileNumber, cardIndex));
        break;
      case CASCADE:
        this.cascadePiles.get(destPileNumber).add(this.moveFrom(source, pileNumber, cardIndex));
        break;
      case FOUNDATION:
        this.foundationPiles.get(destPileNumber).add(this.moveFrom(source, pileNumber, cardIndex));
        break;
      default: // should never get here
    }
  }

  /**
   * Removes and returns the Card at the given pile and given card index from the pile type given.
   *
   * @param source     the type of the piles to remove from
   * @param pileNumber the number of which pile to remove from
   * @param cardIndex  the card index to remove
   * @return the removed card
   * @throws IllegalArgumentException if the type cannot be found
   */
  private Card moveFrom(PileType source, int pileNumber, int cardIndex)
      throws IllegalArgumentException {
    switch (source) {
      case OPEN:
        return this.openPiles.get(pileNumber).remove(cardIndex);
      case CASCADE:
        return this.cascadePiles.get(pileNumber).remove(cardIndex);
      case FOUNDATION:
        return this.foundationPiles.get(pileNumber).remove(cardIndex);
      default:
        throw new IllegalArgumentException("Invalid type");
    }
  }

  /**
   * Gets the number of empty open piles.
   *
   * @return the number of empty open piles as an int
   */
  protected int getNumEmptyOpen() {
    int result = 0;
    for (List<Card> list : this.openPiles) {
      if (list.isEmpty()) {
        result += 1;
      }
    }
    return result;
  }

  /**
   * Gets the number of empty cascade piles.
   *
   * @return the number of empty cascade piles as an int
   */
  protected int getNumEmptyCascade() {
    int result = 0;
    for (List<Card> list : this.cascadePiles) {
      if (list.isEmpty()) {
        result += 1;
      }
    }
    return result;
  }

  /**
   * Removes the part of the cascade pile specified by the pile number and card index and adds it to
   * the specified destination pile.
   *
   * @param pileNumber     the pile to remove from
   * @param cardIndex      the card to start removing from
   * @param destPileNumber the destination to add to
   */
  protected void addAndRemovePile(int pileNumber, int cardIndex, int destPileNumber) {
    int limit = this.getNumCardsInCascadePile(pileNumber);
    for (int i = cardIndex; i < limit; i++) {
      this.cascadePiles.get(destPileNumber)
          .add(this.cascadePiles.get(pileNumber).remove(cardIndex));
    }
  }

  /**
   * Throws an exception if index is not between 0 and high, does nothing otherwise.
   *
   * @param index the given index, which is checked to be in between 0 and high
   * @param high  the higher bound that the index must be lower than
   * @throws IllegalArgumentException if the index is out of the bounds of 0 and high
   */
  private void checkIndex(int index, int high) throws IllegalArgumentException {
    if (!(high == 0 && index == 0)) {
      if (index < 0 || index >= high) {
        throw new IllegalArgumentException("Invalid parameters: "
            + index + " is not in between 0 and " + high);
      }
    }
  }

  /**
   * Returns the amount of piles that the given type has.
   *
   * @param type the type of the pile given
   * @return the amount of piles for the given type
   * @throws IllegalArgumentException if the type cannot be found
   */
  private int checkHowManyPiles(PileType type) throws IllegalArgumentException {
    switch (type) {
      case OPEN:
        return this.getNumOpenPiles();
      case CASCADE:
        return this.getNumCascadePiles();
      case FOUNDATION:
        return 4;
      default:
        throw new IllegalArgumentException("Invalid type");
    }
  }

  /**
   * Returns the amount of cards in the pile type determined by the given type and index.
   *
   * @param type  the type of pile
   * @param index the index of the pile
   * @return the amount of cards in the specified pile
   * @throws IllegalArgumentException if the type cannot be found
   */
  protected int pileSize(PileType type, int index) throws IllegalArgumentException {
    switch (type) {
      case OPEN:
        return this.getNumCardsInOpenPile(index);
      case CASCADE:
        return this.getNumCardsInCascadePile(index);
      case FOUNDATION:
        return this.getNumCardsInFoundationPile(index);
      default:
        throw new IllegalArgumentException("Invalid type");
    }
  }

  @Override
  public boolean isGameOver() {
    int result = 0;
    for (int i = 0; i < 4; i++) {
      result += this.getNumCardsInFoundationPile(i);
    }
    return result == 52;
  }

  @Override
  public int getNumCardsInFoundationPile(int index)
      throws IllegalArgumentException, IllegalStateException {
    this.checkGameStartedException();
    this.checkIndex(index, 4);
    return this.foundationPiles.get(index).size();
  }

  @Override
  public int getNumCascadePiles() {
    if (!this.gameStarted) {
      return -1;
    } else {
      return this.cascadePiles.size();
    }
  }

  @Override
  public int getNumCardsInCascadePile(int index)
      throws IllegalArgumentException, IllegalStateException {
    this.checkGameStartedException();
    this.checkIndex(index, this.getNumCascadePiles());
    return this.cascadePiles.get(index).size();
  }

  @Override
  public int getNumCardsInOpenPile(int index)
      throws IllegalArgumentException, IllegalStateException {
    this.checkGameStartedException();
    this.checkIndex(index, this.getNumOpenPiles());
    return this.openPiles.get(index).size();
  }

  @Override
  public int getNumOpenPiles() {
    if (!this.gameStarted) {
      return -1;
    } else {
      return this.openPiles.size();
    }
  }

  @Override
  public Card getFoundationCardAt(int pileIndex, int cardIndex)
      throws IllegalArgumentException, IllegalStateException {
    this.checkGameStartedException();
    this.checkIndex(pileIndex, 4);
    this.checkIndex(cardIndex, this.getNumCardsInFoundationPile(pileIndex));
    return this.foundationPiles.get(pileIndex).get(cardIndex);
  }

  @Override
  public Card getCascadeCardAt(int pileIndex, int cardIndex)
      throws IllegalArgumentException, IllegalStateException {
    this.checkGameStartedException();
    this.checkIndex(pileIndex, this.getNumCascadePiles());
    this.checkIndex(cardIndex, this.getNumCardsInCascadePile(pileIndex));
    return this.cascadePiles.get(pileIndex).get(cardIndex);
  }

  @Override
  public Card getOpenCardAt(int pileIndex) throws IllegalArgumentException, IllegalStateException {
    this.checkGameStartedException();
    this.checkIndex(pileIndex, this.getNumOpenPiles());
    if (this.getNumCardsInOpenPile(pileIndex) == 0) {
      return null;
    } else {
      return this.openPiles.get(pileIndex).get(0);
    }
  }
}
