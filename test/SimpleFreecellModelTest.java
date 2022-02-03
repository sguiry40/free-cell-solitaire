import static org.junit.Assert.assertEquals;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for SimpleFreecellModel: unit tests to ensure that the freecell model and their
 * methods function correctly.
 */
public class SimpleFreecellModelTest {

  private FreecellModel<Card> model;
  private FreecellModel<Card> shuffledModel;
  private List<Card> standardDeck;

  // initialize examples
  @Before
  public void setup() {
    model = new SimpleFreecellModel();
    shuffledModel = new SimpleFreecellModel(new Random(1));
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
  }

  // Constructor test

  // throws an exception if null is passed as the random for the random constructor
  @Test(expected = IllegalArgumentException.class)
  public void testRandomConstructorNull() {
    new SimpleFreecellModel(null);
  }

  // startGame shuffle tests

  // tests the properties of cascade piles after starting a shuffled game
  // with 6 cascade piles and 6 open piles
  @Test
  public void testStartGameShuffleCascadeProperties() {
    shuffledModel.startGame(standardDeck, 6, 6, true);
    assertEquals(6, shuffledModel.getNumCascadePiles());
    assertEquals(9, shuffledModel.getNumCardsInCascadePile(0));
    assertEquals(9, shuffledModel.getNumCardsInCascadePile(1));
    assertEquals(9, shuffledModel.getNumCardsInCascadePile(2));
    assertEquals(9, shuffledModel.getNumCardsInCascadePile(3));
    assertEquals(8, shuffledModel.getNumCardsInCascadePile(4));
    assertEquals(8, shuffledModel.getNumCardsInCascadePile(5));
  }

  // tests the properties of open piles after starting a shuffled game
  // with 6 cascade piles and 6 open piles
  @Test
  public void testStartGameShuffleOpenProperties() {
    shuffledModel.startGame(standardDeck, 6, 6, true);
    assertEquals(6, shuffledModel.getNumOpenPiles());
    assertEquals(0, shuffledModel.getNumCardsInOpenPile(0));
    assertEquals(0, shuffledModel.getNumCardsInOpenPile(1));
    assertEquals(0, shuffledModel.getNumCardsInOpenPile(2));
    assertEquals(0, shuffledModel.getNumCardsInOpenPile(3));
  }

  // tests the properties of foundation piles after starting a shuffled game
  // with 6 cascade piles and 6 open piles
  @Test
  public void testStartGameShuffleFoundationProperties() {
    shuffledModel.startGame(standardDeck, 6, 6, true);
    assertEquals(6, shuffledModel.getNumOpenPiles());
    assertEquals(0, shuffledModel.getNumCardsInFoundationPile(0));
    assertEquals(0, shuffledModel.getNumCardsInFoundationPile(1));
    assertEquals(0, shuffledModel.getNumCardsInFoundationPile(2));
    assertEquals(0, shuffledModel.getNumCardsInFoundationPile(3));
  }

  // tests the cards of the shuffled game, sees if every card is dealt in the correct position
  @Test
  public void testStartGameShuffleCard() {
    shuffledModel.startGame(model.getDeck(), 6, 6, true);
    System.out.println(shuffledModel.toString());
    assertEquals(new Card(6, "♦"), shuffledModel.getCascadeCardAt(0, 0));
    assertEquals(new Card(1, "♥"), shuffledModel.getCascadeCardAt(1, 0));
    assertEquals(new Card(4, "♣"), shuffledModel.getCascadeCardAt(2, 0));
    assertEquals(new Card(7, "♥"), shuffledModel.getCascadeCardAt(3, 0));
    assertEquals(new Card(3, "♥"), shuffledModel.getCascadeCardAt(4, 0));
    assertEquals(new Card(4, "♥"), shuffledModel.getCascadeCardAt(5, 0));
    assertEquals(new Card(12, "♣"), shuffledModel.getCascadeCardAt(0, 1));
    assertEquals(new Card(8, "♥"), shuffledModel.getCascadeCardAt(1, 1));
    assertEquals(new Card(13, "♣"), shuffledModel.getCascadeCardAt(2, 1));
    assertEquals(new Card(2, "♦"), shuffledModel.getCascadeCardAt(3, 1));
    assertEquals(new Card(10, "♠"), shuffledModel.getCascadeCardAt(4, 1));
    assertEquals(new Card(1, "♦"), shuffledModel.getCascadeCardAt(5, 1));
    assertEquals(new Card(12, "♠"), shuffledModel.getCascadeCardAt(0, 2));
    assertEquals(new Card(10, "♥"), shuffledModel.getCascadeCardAt(1, 2));
    assertEquals(new Card(3, "♦"), shuffledModel.getCascadeCardAt(2, 2));
    assertEquals(new Card(7, "♠"), shuffledModel.getCascadeCardAt(3, 2));
    assertEquals(new Card(7, "♦"), shuffledModel.getCascadeCardAt(4, 2));
    assertEquals(new Card(6, "♠"), shuffledModel.getCascadeCardAt(5, 2));
    assertEquals(new Card(3, "♣"), shuffledModel.getCascadeCardAt(0, 3));
    assertEquals(new Card(10, "♦"), shuffledModel.getCascadeCardAt(1, 3));
    assertEquals(new Card(11, "♣"), shuffledModel.getCascadeCardAt(2, 3));
    assertEquals(new Card(1, "♣"), shuffledModel.getCascadeCardAt(3, 3));
    assertEquals(new Card(11, "♦"), shuffledModel.getCascadeCardAt(4, 3));
    assertEquals(new Card(4, "♦"), shuffledModel.getCascadeCardAt(5, 3));
    assertEquals(new Card(5, "♥"), shuffledModel.getCascadeCardAt(0, 4));
    assertEquals(new Card(11, "♥"), shuffledModel.getCascadeCardAt(1, 4));
    assertEquals(new Card(13, "♦"), shuffledModel.getCascadeCardAt(2, 4));
    assertEquals(new Card(13, "♥"), shuffledModel.getCascadeCardAt(3, 4));
    assertEquals(new Card(9, "♠"), shuffledModel.getCascadeCardAt(4, 4));
    assertEquals(new Card(12, "♦"), shuffledModel.getCascadeCardAt(5, 4));
    assertEquals(new Card(4, "♠"), shuffledModel.getCascadeCardAt(0, 5));
    assertEquals(new Card(8, "♦"), shuffledModel.getCascadeCardAt(1, 5));
    assertEquals(new Card(3, "♠"), shuffledModel.getCascadeCardAt(2, 5));
    assertEquals(new Card(10, "♣"), shuffledModel.getCascadeCardAt(3, 5));
    assertEquals(new Card(8, "♠"), shuffledModel.getCascadeCardAt(4, 5));
    assertEquals(new Card(8, "♣"), shuffledModel.getCascadeCardAt(5, 5));
    assertEquals(new Card(2, "♥"), shuffledModel.getCascadeCardAt(0, 6));
    assertEquals(new Card(5, "♦"), shuffledModel.getCascadeCardAt(1, 6));
    assertEquals(new Card(6, "♣"), shuffledModel.getCascadeCardAt(2, 6));
    assertEquals(new Card(12, "♥"), shuffledModel.getCascadeCardAt(3, 6));
    assertEquals(new Card(6, "♥"), shuffledModel.getCascadeCardAt(4, 6));
    assertEquals(new Card(7, "♣"), shuffledModel.getCascadeCardAt(5, 6));
    assertEquals(new Card(9, "♣"), shuffledModel.getCascadeCardAt(0, 7));
    assertEquals(new Card(9, "♥"), shuffledModel.getCascadeCardAt(1, 7));
    assertEquals(new Card(2, "♠"), shuffledModel.getCascadeCardAt(2, 7));
    assertEquals(new Card(5, "♠"), shuffledModel.getCascadeCardAt(3, 7));
    assertEquals(new Card(13, "♠"), shuffledModel.getCascadeCardAt(4, 7));
    assertEquals(new Card(2, "♣"), shuffledModel.getCascadeCardAt(5, 7));
    assertEquals(new Card(1, "♠"), shuffledModel.getCascadeCardAt(0, 8));
    assertEquals(new Card(9, "♦"), shuffledModel.getCascadeCardAt(1, 8));
    assertEquals(new Card(11, "♠"), shuffledModel.getCascadeCardAt(2, 8));
    assertEquals(new Card(5, "♣"), shuffledModel.getCascadeCardAt(3, 8));
  }

  // move tests

  // throws an exception because a card from the middle of a cascade pile is told to move
  @Test(expected = IllegalArgumentException.class)
  public void testMoveCascadeMiddle() {
    model.startGame(standardDeck, 8, 4, false);
    model.move(PileType.CASCADE, 0, 3, PileType.OPEN, 0);
  }
}