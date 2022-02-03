import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.FreecellModelCreator;
import cs3500.freecell.model.FreecellModelCreator.GameType;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.Card;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for AbstractFreecellModel: unit tests to ensure that the freecell model and their
 * methods function correctly.
 */
public abstract class AbstractFreecellModelTest {

  /**
   * Constructs an instance of the class under test representing a simple freecell model capable of
   * only handling single moves or a multimove freecell model capable of handling single moves and
   * multi moves.
   *
   * @return an instance of the class under test
   */
  protected abstract FreecellModel<Card> create();

  /**
   * Concrete class for testing SimpleFreecellModel implementation of FreecellModel.
   */
  public static final class SimpleFreecellModelCreateTest extends AbstractFreecellModelTest {

    @Override
    protected FreecellModel<Card> create() {
      return FreecellModelCreator.create(GameType.SINGLEMOVE);
    }
  }

  /**
   * Concrete class for testing MultimoveFreecellModel implementation of FreecellModel.
   */
  public static final class MultimoveFreecellModelCreateTest extends AbstractFreecellModelTest {

    @Override
    protected FreecellModel<Card> create() {
      return FreecellModelCreator.create(GameType.MULTIMOVE);
    }
  }

  private FreecellModel<Card> model;
  private List<Card> standardDeck;
  private List<Card> deckSize3;
  private List<Card> deckSize53;
  private List<Card> invalidDeck;

  // initialize examples
  @Before
  public void setup() {
    model = create();
    standardDeck = new ArrayList<>(Arrays.asList(
        new Card(1, "♠"), new Card(2, "♠"), new Card(3, "♠"),
        new Card(4, "♠"), new Card(5, "♠"), new Card(6, "♠"),
        new Card(7, "♠"), new Card(8, "♠"), new Card(9, "♠"),
        new Card(10, "♠"), new Card(11, "♠"), new Card(12, "♠"),
        new Card(13, "♠"), new Card(1, "♣"), new Card(2, "♣"),
        new Card(3, "♣"), new Card(4, "♣"), new Card(5, "♣"),
        new Card(6, "♣"), new Card(7, "♣"), new Card(8, "♣"),
        new Card(9, "♣"), new Card(10, "♣"), new Card(11, "♣"),
        new Card(12, "♣"), new Card(13, "♣"), new Card(1, "♥"),
        new Card(2, "♥"), new Card(3, "♥"), new Card(4, "♥"),
        new Card(5, "♥"), new Card(6, "♥"), new Card(7, "♥"),
        new Card(8, "♥"), new Card(9, "♥"), new Card(10, "♥"),
        new Card(11, "♥"), new Card(12, "♥"), new Card(13, "♥"),
        new Card(1, "♦"), new Card(2, "♦"), new Card(3, "♦"),
        new Card(4, "♦"), new Card(5, "♦"), new Card(6, "♦"),
        new Card(7, "♦"), new Card(8, "♦"), new Card(9, "♦"),
        new Card(10, "♦"), new Card(11, "♦"), new Card(12, "♦"),
        new Card(13, "♦")));
    deckSize3 = new ArrayList<>(Arrays.asList(
        new Card(1, "♠"), new Card(2, "♠"), new Card(3, "♠")));
    deckSize53 = new ArrayList<>(Arrays.asList(
        new Card(1, "♠"), new Card(2, "♠"), new Card(3, "♠"),
        new Card(4, "♠"), new Card(5, "♠"), new Card(6, "♠"),
        new Card(7, "♠"), new Card(8, "♠"), new Card(9, "♠"),
        new Card(10, "♠"), new Card(11, "♠"), new Card(12, "♠"),
        new Card(13, "♠"), new Card(1, "♣"), new Card(2, "♣"),
        new Card(3, "♣"), new Card(4, "♣"), new Card(5, "♣"),
        new Card(6, "♣"), new Card(7, "♣"), new Card(8, "♣"),
        new Card(9, "♣"), new Card(10, "♣"), new Card(11, "♣"),
        new Card(12, "♣"), new Card(13, "♣"), new Card(1, "♥"),
        new Card(2, "♥"), new Card(3, "♥"), new Card(4, "♥"),
        new Card(5, "♥"), new Card(6, "♥"), new Card(7, "♥"),
        new Card(8, "♥"), new Card(9, "♥"), new Card(10, "♥"),
        new Card(11, "♥"), new Card(12, "♥"), new Card(13, "♥"),
        new Card(1, "♦"), new Card(2, "♦"), new Card(3, "♦"),
        new Card(4, "♦"), new Card(5, "♦"), new Card(6, "♦"),
        new Card(7, "♦"), new Card(8, "♦"), new Card(9, "♦"),
        new Card(10, "♦"), new Card(11, "♦"), new Card(12, "♦"),
        new Card(13, "♦"), new Card(4, "♦")));
    invalidDeck = new ArrayList<>(Arrays.asList(
        new Card(1, "♠"), new Card(2, "♠"), new Card(3, "♠"),
        new Card(4, "♠"), new Card(5, "♠"), new Card(6, "♠"),
        new Card(7, "♠"), new Card(8, "♠"), new Card(9, "♠"),
        new Card(10, "♠"), new Card(11, "♠"), new Card(12, "♠"),
        new Card(13, "♠"), new Card(1, "♣"), new Card(2, "♣"),
        new Card(3, "♣"), new Card(4, "♣"), new Card(5, "♣"),
        new Card(6, "♣"), new Card(7, "♣"), new Card(8, "♣"),
        new Card(9, "♣"), new Card(10, "♣"), new Card(11, "♣"),
        new Card(12, "♣"), new Card(13, "♣"), new Card(1, "♥"),
        new Card(2, "♥"), new Card(3, "♥"), new Card(4, "♥"),
        new Card(5, "♥"), new Card(7, "♥"), new Card(7, "♥"),
        new Card(8, "♥"), new Card(9, "♥"), new Card(10, "♥"),
        new Card(11, "♥"), new Card(12, "♥"), new Card(13, "♥"),
        new Card(1, "♦"), new Card(2, "♦"), new Card(3, "♦"),
        new Card(4, "♦"), new Card(5, "♦"), new Card(6, "♦"),
        new Card(7, "♦"), new Card(8, "♦"), new Card(9, "♦"),
        new Card(10, "♦"), new Card(11, "♦"), new Card(12, "♦"),
        new Card(13, "♦")));
  }

  // getDeck tests

  // tests the normal functionality of the getDeck function
  @Test
  public void testGetDeck() {
    assertEquals(standardDeck, model.getDeck());
  }

  // startGame tests

  // throws an exception when null is passed as the deck
  @Test(expected = IllegalArgumentException.class)
  public void testStartGameDeckNull() {
    model.startGame(null, 8, 4, false);
  }

  // throws an exception because the given deck is too small
  @Test(expected = IllegalArgumentException.class)
  public void testStartGameSmallDeck() {
    model.startGame(deckSize3, 8, 4, false);
  }

  // throws an exception because the given deck is too large
  @Test(expected = IllegalArgumentException.class)
  public void testStartGameLargeDeck() {
    model.startGame(deckSize53, 8, 4, false);
  }

  // throws an exception because the deck was size 52, but invalid
  @Test(expected = IllegalArgumentException.class)
  public void testStartGameInvalidDeck() {
    model.startGame(invalidDeck, 8, 4, false);
  }

  // throws an exception because the number of cascade piles is too small
  @Test(expected = IllegalArgumentException.class)
  public void testStartGameSmallCascade() {
    model.startGame(standardDeck, 3, 4, false);
  }

  // throws an exception because the number of open piles is too small
  @Test(expected = IllegalArgumentException.class)
  public void testStartGameSmallOpen() {
    model.startGame(standardDeck, 3, 0, false);
  }

  // throws an exception because the number of cascade piles is too large
  @Test(expected = IllegalArgumentException.class)
  public void testStartGameLargeCascade() {
    model.startGame(standardDeck, 53, 4, false);
  }

  // throws an exception because the number of open piles is too large
  @Test(expected = IllegalArgumentException.class)
  public void testStartGameLargeOpen() {
    model.startGame(standardDeck, 8, 53, false);
  }

  // tests the properties of cascade piles after starting a normal game
  // with 8 cascade piles and 4 open piles
  @Test
  public void testStartGameNormalCascadeProperties() {
    model.startGame(standardDeck, 8, 4, false);
    assertEquals(8, model.getNumCascadePiles());
    assertEquals(7, model.getNumCardsInCascadePile(0));
    assertEquals(7, model.getNumCardsInCascadePile(1));
    assertEquals(7, model.getNumCardsInCascadePile(2));
    assertEquals(7, model.getNumCardsInCascadePile(3));
    assertEquals(6, model.getNumCardsInCascadePile(4));
    assertEquals(6, model.getNumCardsInCascadePile(5));
    assertEquals(6, model.getNumCardsInCascadePile(6));
    assertEquals(6, model.getNumCardsInCascadePile(7));
  }

  // tests the properties of open piles after starting a normal game
  // with 8 cascade piles and 4 open piles
  @Test
  public void testStartGameNormalOpenProperties() {
    model.startGame(standardDeck, 8, 4, false);
    assertEquals(4, model.getNumOpenPiles());
    assertEquals(0, model.getNumCardsInOpenPile(0));
    assertEquals(0, model.getNumCardsInOpenPile(1));
    assertEquals(0, model.getNumCardsInOpenPile(2));
    assertEquals(0, model.getNumCardsInOpenPile(3));
  }

  // tests the properties of foundation piles after starting a normal game
  // with 8 cascade piles and 4 open piles
  @Test
  public void testStartGameNormalFoundationProperties() {
    model.startGame(standardDeck, 8, 4, false);
    assertEquals(0, model.getNumCardsInFoundationPile(0));
    assertEquals(0, model.getNumCardsInFoundationPile(1));
    assertEquals(0, model.getNumCardsInFoundationPile(2));
    assertEquals(0, model.getNumCardsInFoundationPile(3));
  }

  // tests the cards of the normal game, sees if every card is dealt in the correct position
  @Test
  public void testStartGameNormalCard() {
    model.startGame(standardDeck, 8, 4, false);
    assertEquals(new Card(1, "♠"), model.getCascadeCardAt(0, 0));
    assertEquals(new Card(2, "♠"), model.getCascadeCardAt(1, 0));
    assertEquals(new Card(3, "♠"), model.getCascadeCardAt(2, 0));
    assertEquals(new Card(4, "♠"), model.getCascadeCardAt(3, 0));
    assertEquals(new Card(5, "♠"), model.getCascadeCardAt(4, 0));
    assertEquals(new Card(6, "♠"), model.getCascadeCardAt(5, 0));
    assertEquals(new Card(7, "♠"), model.getCascadeCardAt(6, 0));
    assertEquals(new Card(8, "♠"), model.getCascadeCardAt(7, 0));
    assertEquals(new Card(9, "♠"), model.getCascadeCardAt(0, 1));
    assertEquals(new Card(10, "♠"), model.getCascadeCardAt(1, 1));
    assertEquals(new Card(11, "♠"), model.getCascadeCardAt(2, 1));
    assertEquals(new Card(12, "♠"), model.getCascadeCardAt(3, 1));
    assertEquals(new Card(13, "♠"), model.getCascadeCardAt(4, 1));
    assertEquals(new Card(1, "♣"), model.getCascadeCardAt(5, 1));
    assertEquals(new Card(2, "♣"), model.getCascadeCardAt(6, 1));
    assertEquals(new Card(3, "♣"), model.getCascadeCardAt(7, 1));
    assertEquals(new Card(4, "♣"), model.getCascadeCardAt(0, 2));
    assertEquals(new Card(5, "♣"), model.getCascadeCardAt(1, 2));
    assertEquals(new Card(6, "♣"), model.getCascadeCardAt(2, 2));
    assertEquals(new Card(7, "♣"), model.getCascadeCardAt(3, 2));
    assertEquals(new Card(8, "♣"), model.getCascadeCardAt(4, 2));
    assertEquals(new Card(9, "♣"), model.getCascadeCardAt(5, 2));
    assertEquals(new Card(10, "♣"), model.getCascadeCardAt(6, 2));
    assertEquals(new Card(11, "♣"), model.getCascadeCardAt(7, 2));
    assertEquals(new Card(12, "♣"), model.getCascadeCardAt(0, 3));
    assertEquals(new Card(13, "♣"), model.getCascadeCardAt(1, 3));
    assertEquals(new Card(1, "♥"), model.getCascadeCardAt(2, 3));
    assertEquals(new Card(2, "♥"), model.getCascadeCardAt(3, 3));
    assertEquals(new Card(3, "♥"), model.getCascadeCardAt(4, 3));
    assertEquals(new Card(4, "♥"), model.getCascadeCardAt(5, 3));
    assertEquals(new Card(5, "♥"), model.getCascadeCardAt(6, 3));
    assertEquals(new Card(6, "♥"), model.getCascadeCardAt(7, 3));
    assertEquals(new Card(7, "♥"), model.getCascadeCardAt(0, 4));
    assertEquals(new Card(8, "♥"), model.getCascadeCardAt(1, 4));
    assertEquals(new Card(9, "♥"), model.getCascadeCardAt(2, 4));
    assertEquals(new Card(10, "♥"), model.getCascadeCardAt(3, 4));
    assertEquals(new Card(11, "♥"), model.getCascadeCardAt(4, 4));
    assertEquals(new Card(12, "♥"), model.getCascadeCardAt(5, 4));
    assertEquals(new Card(13, "♥"), model.getCascadeCardAt(6, 4));
    assertEquals(new Card(1, "♦"), model.getCascadeCardAt(7, 4));
    assertEquals(new Card(2, "♦"), model.getCascadeCardAt(0, 5));
    assertEquals(new Card(3, "♦"), model.getCascadeCardAt(1, 5));
    assertEquals(new Card(4, "♦"), model.getCascadeCardAt(2, 5));
    assertEquals(new Card(5, "♦"), model.getCascadeCardAt(3, 5));
    assertEquals(new Card(6, "♦"), model.getCascadeCardAt(4, 5));
    assertEquals(new Card(7, "♦"), model.getCascadeCardAt(5, 5));
    assertEquals(new Card(8, "♦"), model.getCascadeCardAt(6, 5));
    assertEquals(new Card(9, "♦"), model.getCascadeCardAt(7, 5));
    assertEquals(new Card(10, "♦"), model.getCascadeCardAt(0, 6));
    assertEquals(new Card(11, "♦"), model.getCascadeCardAt(1, 6));
    assertEquals(new Card(12, "♦"), model.getCascadeCardAt(2, 6));
    assertEquals(new Card(13, "♦"), model.getCascadeCardAt(3, 6));
  }

  // isGameOver tests

  // throws an exception because the game has not started yet
  @Test(expected = IllegalStateException.class)
  public void testIsGameOverNotStarted() {
    model.isGameOver();
  }

  // tests the normal functionality of the isGameOver method with a 52 cascade pile deck
  // until it is true
  @Test
  public void testIsGameOverNormal() {
    model.startGame(standardDeck, 52, 4, false);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION, 0);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION, 0);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 0);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 0);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 5, 0, PileType.FOUNDATION, 0);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 6, 0, PileType.FOUNDATION, 0);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 7, 0, PileType.FOUNDATION, 0);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 8, 0, PileType.FOUNDATION, 0);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 9, 0, PileType.FOUNDATION, 0);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 10, 0, PileType.FOUNDATION, 0);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 11, 0, PileType.FOUNDATION, 0);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 12, 0, PileType.FOUNDATION, 0);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 13, 0, PileType.FOUNDATION, 1);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 14, 0, PileType.FOUNDATION, 1);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 15, 0, PileType.FOUNDATION, 1);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 16, 0, PileType.FOUNDATION, 1);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 17, 0, PileType.FOUNDATION, 1);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 18, 0, PileType.FOUNDATION, 1);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 19, 0, PileType.FOUNDATION, 1);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 20, 0, PileType.FOUNDATION, 1);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 21, 0, PileType.FOUNDATION, 1);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 22, 0, PileType.FOUNDATION, 1);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 23, 0, PileType.FOUNDATION, 1);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 24, 0, PileType.FOUNDATION, 1);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 25, 0, PileType.FOUNDATION, 1);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 26, 0, PileType.FOUNDATION, 2);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 27, 0, PileType.FOUNDATION, 2);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 28, 0, PileType.FOUNDATION, 2);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 29, 0, PileType.FOUNDATION, 2);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 30, 0, PileType.FOUNDATION, 2);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 31, 0, PileType.FOUNDATION, 2);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 32, 0, PileType.FOUNDATION, 2);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 33, 0, PileType.FOUNDATION, 2);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 34, 0, PileType.FOUNDATION, 2);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 35, 0, PileType.FOUNDATION, 2);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 36, 0, PileType.FOUNDATION, 2);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 37, 0, PileType.FOUNDATION, 2);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 38, 0, PileType.FOUNDATION, 2);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 39, 0, PileType.FOUNDATION, 3);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 40, 0, PileType.FOUNDATION, 3);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 41, 0, PileType.FOUNDATION, 3);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 42, 0, PileType.FOUNDATION, 3);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 43, 0, PileType.FOUNDATION, 3);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 44, 0, PileType.FOUNDATION, 3);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 45, 0, PileType.FOUNDATION, 3);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 46, 0, PileType.FOUNDATION, 3);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 47, 0, PileType.FOUNDATION, 3);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 48, 0, PileType.FOUNDATION, 3);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 49, 0, PileType.FOUNDATION, 3);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 50, 0, PileType.FOUNDATION, 3);
    assertFalse(model.isGameOver());
    model.move(PileType.CASCADE, 51, 0, PileType.FOUNDATION, 3);
    assertTrue(model.isGameOver());
  }

  // move tests

  // throws an exception because the game has not started yet
  @Test(expected = IllegalStateException.class)
  public void testMoveNotStarted() {
    model.move(PileType.CASCADE, 0, 4, PileType.OPEN, 2);
  }

  // throws an exception because null is passed as the source type
  @Test(expected = IllegalArgumentException.class)
  public void testMoveNullSource() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(null, 0, 0, PileType.OPEN, 0);
  }

  // throws an exception because null is passed as the destination type
  @Test(expected = IllegalArgumentException.class)
  public void testMoveNullDest() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.OPEN, 0, 0, null, 0);
  }

  // throws an exception because the open source pile index is too small
  @Test(expected = IllegalArgumentException.class)
  public void testMoveOpenPileIndexSmall() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.OPEN, -2, 0, PileType.OPEN, 0);
  }

  // throws an exception because the open source pile index is too large
  @Test(expected = IllegalArgumentException.class)
  public void testMoveOpenPileIndexLarge() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.OPEN, 5, 0, PileType.OPEN, 0);
  }

  // throws an exception because the cascade source pile index is too small
  @Test(expected = IllegalArgumentException.class)
  public void testMoveCascadePileIndexSmall() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.CASCADE, -3, 0, PileType.OPEN, 0);
  }

  // throws an exception because the cascade source pile index is too large
  @Test(expected = IllegalArgumentException.class)
  public void testMoveCascadePileIndexLarge() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.CASCADE, 10, 0, PileType.OPEN, 0);
  }

  // throws an exception because the open source card index is too small
  @Test(expected = IllegalArgumentException.class)
  public void testMoveOpenCardIndexSmall() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.OPEN, 0, -1, PileType.OPEN, 0);
  }

  // throws an exception because the open source card index is too large
  @Test(expected = IllegalArgumentException.class)
  public void testMoveOpenCardIndexLarge() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.OPEN, 0, 1, PileType.OPEN, 0);
  }

  // throws an exception because the cascade source card index is too small
  @Test(expected = IllegalArgumentException.class)
  public void testMoveCascadeCardIndexSmall() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.CASCADE, 0, -3, PileType.OPEN, 0);
  }

  // throws an exception because the cascade source card index is too large
  @Test(expected = IllegalArgumentException.class)
  public void testMoveCascadeCardIndexLarge() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.CASCADE, 0, 9, PileType.OPEN, 0);
  }

  // throws an exception because the open destination pile index is too small
  @Test(expected = IllegalArgumentException.class)
  public void testMoveOpenDestPileIndexSmall() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.CASCADE, 0, 2, PileType.OPEN, -3);
  }

  // throws an exception because the open destination pile index is too large
  @Test(expected = IllegalArgumentException.class)
  public void testMoveOpenDestPileIndexLarge() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.CASCADE, 0, 2, PileType.OPEN, 8);
  }

  // throws an exception because the cascade destination pile index is too small
  @Test(expected = IllegalArgumentException.class)
  public void testMoveCascadeDestPileIndexSmall() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.CASCADE, 0, 2, PileType.CASCADE, -5);
  }

  // throws an exception because the cascade destination pile index is too large
  @Test(expected = IllegalArgumentException.class)
  public void testMoveCascadeDestPileIndexLarge() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.CASCADE, 0, 2, PileType.CASCADE, 11);
  }

  // throws an exception because the foundation destination pile index is too small
  @Test(expected = IllegalArgumentException.class)
  public void testMoveFoundationDestPileIndexSmall() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.CASCADE, 0, 2, PileType.FOUNDATION, -4);
  }

  // throws an exception because the foundation destination pile index is too large
  @Test(expected = IllegalArgumentException.class)
  public void testMoveFoundationDestPileIndexLarge() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.CASCADE, 0, 2, PileType.FOUNDATION, 6);
  }

  // throws an exception because the foundation pile is the source
  @Test(expected = IllegalArgumentException.class)
  public void testMoveFoundationSource() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.FOUNDATION, 0, 2, PileType.OPEN, 0);
  }

  // throws an exception because a card in an open pile is told to move to a full open pile
  @Test(expected = IllegalArgumentException.class)
  public void testMoveOpenToFullOpen() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.CASCADE, 0, 6, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 0, 5, PileType.OPEN, 1);
    model.move(PileType.OPEN, 0, 0, PileType.OPEN, 1);
  }

  // throws an exception because a card in an open pile is told to move to a cascade pile
  // where the last card is not one more and different color than the source card
  @Test(expected = IllegalArgumentException.class)
  public void testMoveOpenToInvalidCascade() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.CASCADE, 0, 6, PileType.OPEN, 0);
    model.move(PileType.OPEN, 0, 0, PileType.CASCADE, 3);
  }

  // throws an exception because a card in an open pile is told to move to a foundation pile
  // where the last card is not one less and same suit as the source card
  @Test(expected = IllegalArgumentException.class)
  public void testMoveOpenToInvalidFoundation() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.CASCADE, 0, 6, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 0, 5, PileType.FOUNDATION, 0);
    model.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 0);
  }

  // throws an exception because a card in an open pile is told to move to an empty foundation
  // pile but the source card is not an ace
  @Test(expected = IllegalArgumentException.class)
  public void testMoveOpenToEmptyFoundationNotAce() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.CASCADE, 0, 6, PileType.OPEN, 0);
    model.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 0);
  }

  // throws an exception because the last card in a cascade pile is told to
  // move to a full open pile
  @Test(expected = IllegalArgumentException.class)
  public void testMoveCascadeToFullOpen() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.CASCADE, 0, 6, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 0, 5, PileType.OPEN, 0);
  }

  // throws an exception because the last card in a cascade pile is told to move to a cascade pile
  // where the last card is not one more and different color than the source card
  @Test(expected = IllegalArgumentException.class)
  public void testMoveCascadeToInvalidCascade() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.CASCADE, 0, 6, PileType.CASCADE, 1);
  }

  // throws an exception because the last card in a cascade pile is told to move to a
  // foundation pile where the last card is not one less and same suit as the source card
  @Test(expected = IllegalArgumentException.class)
  public void testMoveCascadeToInvalidFoundation() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.CASCADE, 0, 6, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 0, 5, PileType.FOUNDATION, 0);
  }

  // throws an exception because the last card in a cascade pile is told to move to a
  // foundation pile but the source code is not an ace
  @Test(expected = IllegalArgumentException.class)
  public void testMoveCascadeToEmptyFoundationNotAce() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.CASCADE, 0, 6, PileType.FOUNDATION, 0);
  }

  // tests the normal functionality of the move method when moving from an open pile to open pile
  @Test
  public void testMoveOpenToOpen() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.CASCADE, 0, 6, PileType.OPEN, 0);
    model.move(PileType.OPEN, 0, 0, PileType.OPEN, 1);
    assertEquals(new Card(10, "♦"), model.getOpenCardAt(1));
  }

  // tests the normal functionality of the move method when moving from an open pile to the end
  // of a cascade pile
  @Test
  public void testMoveOpenToCascade() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.CASCADE, 0, 6, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 0, 5, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 0, 4, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 1, 6, PileType.OPEN, 3);
    model.move(PileType.OPEN, 3, 0, PileType.CASCADE, 0);
    assertEquals(new Card(11, "♦"), model.getCascadeCardAt(0, 4));
  }

  // tests the normal functionality of the move method when moving from an open pile to an
  // empty cascade pile
  @Test
  public void testOpenToEmptyCascade() {
    model.startGame(standardDeck, 8, 7, false);
    model.move(PileType.CASCADE, 0, 6, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 0, 5, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 0, 4, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 0, 3, PileType.OPEN, 3);
    model.move(PileType.CASCADE, 0, 2, PileType.OPEN, 4);
    model.move(PileType.CASCADE, 0, 1, PileType.OPEN, 5);
    model.move(PileType.CASCADE, 0, 0, PileType.OPEN, 6);
    model.move(PileType.OPEN, 3, 0, PileType.CASCADE, 0);
    assertEquals(new Card(12, "♣"), model.getCascadeCardAt(0, 0));
  }


  // tests the normal functionality of the move method when moving from an open pile to the end of
  // a foundation pile
  @Test
  public void testOpenToFoundation() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 0, 6, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 0, 5, PileType.OPEN, 2);
    model.move(PileType.OPEN, 2, 0, PileType.FOUNDATION, 0);
    assertEquals(new Card(2, "♦"), model.getFoundationCardAt(0, 1));
  }

  // tests the normal functionality of the move method when moving an ace from an open pile
  // to an empty foundation pile
  @Test
  public void testOpenToFoundationAce() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 7, 4, PileType.OPEN, 1);
    model.move(PileType.OPEN, 1, 0, PileType.FOUNDATION, 0);
    assertEquals(new Card(1, "♦"), model.getFoundationCardAt(0, 0));
  }

  // tests the normal functionality of the move method when moving from a cascade card to an open
  // pile
  @Test
  public void testCascadeToOpen() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
    assertEquals(new Card(9, "♦"), model.getOpenCardAt(0));
  }

  // tests the normal functionality of the move method when moving from a cascade card to the end
  // of another cascade pile
  @Test
  public void testCascadeToCascade() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 7, 4, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 7, 3, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 7, 2, PileType.CASCADE, 2);
    assertEquals(new Card(11, "♣"), model.getCascadeCardAt(2, 7));
  }

  // tests the normal functionality of the move method when moving from a cascade card to an empty
  // cascade pile
  @Test
  public void testCascadeToCascadeEmpty() {
    model.startGame(standardDeck, 8, 7, false);
    model.move(PileType.CASCADE, 0, 6, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 0, 5, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 0, 4, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 0, 3, PileType.OPEN, 3);
    model.move(PileType.CASCADE, 0, 2, PileType.OPEN, 4);
    model.move(PileType.CASCADE, 0, 1, PileType.OPEN, 5);
    model.move(PileType.CASCADE, 0, 0, PileType.OPEN, 6);
    model.move(PileType.CASCADE, 1, 6, PileType.CASCADE, 0);
    assertEquals(new Card(11, "♦"), model.getCascadeCardAt(0, 0));
  }

  // tests the normal functionality of the move method when moving from a cascade card to the end
  // of a foundation pile

  @Test
  public void testCascadeToFoundation() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 0, 6, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 0, 5, PileType.FOUNDATION, 0);
    assertEquals(new Card(2, "♦"), model.getFoundationCardAt(0, 1));
  }

  // tests the normal functionality of the move method when moving from a cascade card to an empty
  // foundation pile, meaning the card is an ace
  @Test
  public void testCascadeToFoundationAce() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 0);
    assertEquals(new Card(1, "♦"), model.getFoundationCardAt(0, 0));
  }

  // getNumCardsInFoundationPile tests

  // throws an exception because the game has not started yet
  @Test(expected = IllegalStateException.class)
  public void testGetNumCardsInFoundationPileNotStarted() {
    model.getNumCardsInFoundationPile(3);
  }

  // throws an exception because the index was less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testGetNumCardsInFoundationPileSmallIndex() {
    model.startGame(standardDeck, 8, 4, false);
    model.getNumCardsInFoundationPile(-1);
  }

  // throws an exception because the index was larger than the amount of
  // foundation piles, 4
  @Test(expected = IllegalArgumentException.class)
  public void testGetNumCardsInFoundationPileLargeIndex() {
    model.startGame(standardDeck, 8, 4, false);
    model.getNumCardsInFoundationPile(4);
    model.getNumCardsInFoundationPile(8);
  }

  // tests the normal functionality of the getNumCardsInFoundationPile method
  // (added an open slot to get the second ace)
  @Test
  public void testGetNumCardsInFoundationPileNormal() {
    model.startGame(standardDeck, 8, 5, false);
    assertEquals(0, model.getNumCardsInFoundationPile(3));
    model.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 0);
    assertEquals(1, model.getNumCardsInFoundationPile(0));
    model.move(PileType.CASCADE, 0, 6, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 0, 5, PileType.FOUNDATION, 0);
    assertEquals(2, model.getNumCardsInFoundationPile(0));
    model.move(PileType.CASCADE, 2, 6, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 2, 5, PileType.OPEN, 3);
    model.move(PileType.CASCADE, 2, 4, PileType.OPEN, 4);
    model.move(PileType.CASCADE, 2, 3, PileType.FOUNDATION, 1);
    assertEquals(1, model.getNumCardsInFoundationPile(1));
  }

  // getNumCascadePiles tests

  // returns -1 because the game has not started yet
  @Test
  public void testGetNumCascadePilesNotStarted() {
    assertEquals(-1, model.getNumCascadePiles());
  }

  // tests the normal functionality of the getNumCascadePiles method
  @Test
  public void testGetNumCascadePilesNormal() {
    model.startGame(standardDeck, 8, 4, false);
    assertEquals(8, model.getNumCascadePiles());
    model.startGame(standardDeck, 12, 4, false);
    assertEquals(12, model.getNumCascadePiles());
  }

  // getNumCardsInCascadePile tests

  // throws an exception because the game has not started yet
  @Test(expected = IllegalStateException.class)
  public void testGetNumCardsInCascadePileNotStarted() {
    model.getNumCardsInCascadePile(2);
  }

  // throws an exception because the index is too small
  @Test(expected = IllegalArgumentException.class)
  public void testGetNumCardsInCascadePileSmallIndex() {
    model.startGame(standardDeck, 8, 4, false);
    model.getNumCardsInCascadePile(-2);
  }

  // throws an exception because the index is too large
  @Test(expected = IllegalArgumentException.class)
  public void testGetNumCardsInCascadePileLargeIndex() {
    model.startGame(standardDeck, 8, 4, false);
    model.getNumCardsInCascadePile(12);
  }

  // tests the normal functionality of the getNumCardsInCascadePile method
  @Test
  public void testGetNumCardsInCascadePileNormal() {
    model.startGame(standardDeck, 8, 4, false);
    assertEquals(7, model.getNumCardsInCascadePile(2));
    model.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 7, 4, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 7, 3, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 7, 2, PileType.CASCADE, 2);
    assertEquals(8, model.getNumCardsInCascadePile(2));
  }

  // getNumCardsInOpenPile tests

  // throws an exception because the game has not started yet
  @Test(expected = IllegalStateException.class)
  public void testGetNumCardsInOpenPileNotStarted() {
    model.getNumCardsInOpenPile(3);
  }

  // throws an exception because the index is too small
  @Test(expected = IllegalArgumentException.class)
  public void testGetNumCardsInOpenPileSmallIndex() {
    model.startGame(standardDeck, 8, 4, false);
    model.getNumCardsInOpenPile(-3);
  }

  // throws an exception because the index is too large
  @Test(expected = IllegalArgumentException.class)
  public void testGetNumCardsInOpenPileLargeIndex() {
    model.startGame(standardDeck, 8, 4, false);
    model.getNumCardsInOpenPile(7);
  }

  // tests the normal functionality of the getNumCardsInOpenPile method
  @Test
  public void testGetNumCardsInOpenPileNormal() {
    model.startGame(standardDeck, 8, 4, false);
    assertEquals(0, model.getNumCardsInOpenPile(0));
    assertEquals(0, model.getNumCardsInOpenPile(1));
    model.move(PileType.CASCADE, 0, 6, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 0, 5, PileType.OPEN, 1);
    assertEquals(1, model.getNumCardsInOpenPile(0));
    assertEquals(1, model.getNumCardsInOpenPile(1));
  }

  // getNumOpenPiles tests

  // returns -1 because the game has not started yet
  @Test
  public void testGetNumOpenPilesNotStarted() {
    assertEquals(-1, model.getNumOpenPiles());
  }

  // tests the normal functionality of the getNumOpenPiles method
  @Test
  public void testGetNumOpenPilesNormal() {
    model.startGame(standardDeck, 8, 4, false);
    assertEquals(4, model.getNumOpenPiles());
    model.startGame(standardDeck, 6, 20, false);
    assertEquals(20, model.getNumOpenPiles());
  }

  // getFoundationCardAt tests

  // throws an exception because the game has not started yet
  @Test(expected = IllegalStateException.class)
  public void testGetFoundationCardAtNotStarted() {
    model.getFoundationCardAt(0, 2);
  }

  // throws an exception because the pile index is too small
  @Test(expected = IllegalArgumentException.class)
  public void testGetFoundationCardAtSmallPileIndex() {
    model.startGame(standardDeck, 8, 4, false);
    model.getFoundationCardAt(-5, 0);
  }

  // throws an exception because the pile index is too large
  @Test(expected = IllegalArgumentException.class)
  public void testGetFoundationCardAtLargePileIndex() {
    model.startGame(standardDeck, 8, 4, false);
    model.getFoundationCardAt(5, 0);
  }

  // throws an exception because the card index is too small
  @Test(expected = IllegalArgumentException.class)
  public void testGetFoundationCardAtSmallCardIndex() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 0);
    model.getFoundationCardAt(0, -4);
  }

  // throws an exception because the card index is too large
  @Test(expected = IllegalArgumentException.class)
  public void testGetFoundationCardAtLargeCardIndex() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 0);
    model.getFoundationCardAt(0, 1);
  }

  // tests the normal functionality of the getFoundationCardAt method
  // (added an open slot to get second ace)
  @Test
  public void testGetFoundationCardAtNormal() {
    model.startGame(standardDeck, 8, 5, false);
    model.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 0);
    assertEquals(new Card(1, "♦"), model.getFoundationCardAt(0, 0));
    model.move(PileType.CASCADE, 0, 6, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 0, 5, PileType.FOUNDATION, 0);
    assertEquals(new Card(2, "♦"), model.getFoundationCardAt(0, 1));
    model.move(PileType.CASCADE, 2, 6, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 2, 5, PileType.OPEN, 3);
    model.move(PileType.CASCADE, 2, 4, PileType.OPEN, 4);
    model.move(PileType.CASCADE, 2, 3, PileType.FOUNDATION, 1);
    assertEquals(new Card(1, "♥"), model.getFoundationCardAt(1, 0));
  }

  // getCascadeCardAt tests

  // throws an exception because the game has not started yet
  @Test(expected = IllegalStateException.class)
  public void testGetCascadeCardAtNotStarted() {
    model.getCascadeCardAt(0, 0);
  }

  // throws an exception because the pile index is too small
  @Test(expected = IllegalArgumentException.class)
  public void testGetCascadeCardAtSmallPileIndex() {
    model.startGame(standardDeck, 8, 4, false);
    model.getCascadeCardAt(-2, 3);
  }

  // throws an exception because the pile index is too large
  @Test(expected = IllegalArgumentException.class)
  public void testGetCascadeCardAtLargePileIndex() {
    model.startGame(standardDeck, 8, 4, false);
    model.getCascadeCardAt(9, 3);
  }

  // throws an exception because the card index is too small
  @Test(expected = IllegalArgumentException.class)
  public void testGetCascadeCardAtSmallCardIndex() {
    model.startGame(standardDeck, 8, 4, false);
    model.getCascadeCardAt(2, -2);
  }

  // throws an exception because the card index is too large
  @Test(expected = IllegalArgumentException.class)
  public void testGetCascadeCardAtLargeCardIndex() {
    model.startGame(standardDeck, 8, 4, false);
    model.getCascadeCardAt(2, 7);
  }

  // tests the normal functionality of the getCascadeCardAt method
  @Test
  public void testGetCascadeCardAtNormal() {
    model.startGame(standardDeck, 8, 4, false);
    assertEquals(new Card(12, "♠"), model.getCascadeCardAt(3, 1));
    assertEquals(new Card(7, "♣"), model.getCascadeCardAt(3, 2));
    assertEquals(new Card(13, "♠"), model.getCascadeCardAt(4, 1));
  }

  // getOpenCardAt tests

  // throws an exception because the game has not started yet
  @Test(expected = IllegalStateException.class)
  public void testGetOpenCardAtNotStarted() {
    model.getOpenCardAt(2);
  }

  // throws an exception because the pile index is too small
  @Test(expected = IllegalArgumentException.class)
  public void testGetOpenCardAtSmallPileIndex() {
    model.startGame(standardDeck, 8, 4, false);
    model.getOpenCardAt(-2);
  }

  // throws an exception because the pile index is too large
  @Test(expected = IllegalArgumentException.class)
  public void testGetOpenCardAtLargePileIndex() {
    model.startGame(standardDeck, 8, 4, false);
    model.getOpenCardAt(7);
  }

  // returns null because the given open pile is empty
  @Test
  public void testGetOpenCardAtEmptyNull() {
    model.startGame(standardDeck, 8, 4, false);
    assertNull(model.getOpenCardAt(2));
  }

  // tests the normal functionality of the getOpenCardAt method
  @Test
  public void testGetOpenCardAtNormal() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.CASCADE, 0, 6, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 4, 5, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 4, 4, PileType.OPEN, 3);
    assertEquals(new Card(10, "♦"), model.getOpenCardAt(1));
    assertEquals(new Card(11, "♥"), model.getOpenCardAt(3));
  }
}