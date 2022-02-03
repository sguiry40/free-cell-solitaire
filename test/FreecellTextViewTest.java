import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw04.MultimoveFreecellModel;
import cs3500.freecell.view.FreecellTextView;
import cs3500.freecell.view.FreecellView;
import java.io.IOException;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for FreecellTextView: unit tests to ensure that toString functions correctly.
 */
public class FreecellTextViewTest {


  private FreecellModel<Card> model;
  private FreecellModel<Card> shuffledSimple;
  private FreecellModel<Card> shuffledMulti;
  private FreecellView view;
  private FreecellView shuffledViewSimple;
  private FreecellView shuffledViewMulti;
  private Appendable appendable;
  private FreecellView appendableView;
  private FreecellView invalidView;
  FreecellView appendableViewSimple;
  FreecellView appendableViewMulti;
  private String toString1;
  private String toString2;
  private String toString3;
  private String toString4;
  private String toString5;

  // initialize examples
  @Before
  public void setup() {
    model = new SimpleFreecellModel();
    shuffledSimple = new SimpleFreecellModel(new Random(1));
    shuffledMulti = new MultimoveFreecellModel(new Random(1));
    view = new FreecellTextView(model);
    shuffledViewSimple = new FreecellTextView(shuffledSimple);
    shuffledViewMulti = new FreecellTextView(shuffledMulti);
    appendable = new StringBuilder();
    Appendable invalidAppendable = new InvalidAppendable();
    appendableView = new FreecellTextView(model, appendable);
    invalidView = new FreecellTextView(model, invalidAppendable);
    appendableViewSimple = new FreecellTextView(shuffledSimple, appendable);
    appendableViewMulti = new FreecellTextView(shuffledMulti, appendable);
    toString1 = "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n"
        + "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n"
        + "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n"
        + "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦";
    toString2 = "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "O6:\n"
        + "C1: A♠, J♠, 8♣, 5♥, 2♦, Q♦\n"
        + "C2: 2♠, Q♠, 9♣, 6♥, 3♦, K♦\n"
        + "C3: 3♠, K♠, 10♣, 7♥, 4♦\n"
        + "C4: 4♠, A♣, J♣, 8♥, 5♦\n"
        + "C5: 5♠, 2♣, Q♣, 9♥, 6♦\n"
        + "C6: 6♠, 3♣, K♣, 10♥, 7♦\n"
        + "C7: 7♠, 4♣, A♥, J♥, 8♦\n"
        + "C8: 8♠, 5♣, 2♥, Q♥, 9♦\n"
        + "C9: 9♠, 6♣, 3♥, K♥, 10♦\n"
        + "C10: 10♠, 7♣, 4♥, A♦, J♦";
    toString3 = "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "C1: 6♦, K♣, 7♦, 5♥, 3♠, 6♥, A♠\n"
        + "C2: A♥, 2♦, 6♠, J♥, 10♣, 7♣, 9♦\n"
        + "C3: 4♣, 10♠, 3♣, K♦, 8♠, 9♣, J♠\n"
        + "C4: 7♥, A♦, 10♦, K♥, 8♣, 9♥, 5♣\n"
        + "C5: 3♥, Q♠, J♣, 9♠, 2♥, 2♠\n"
        + "C6: 4♥, 10♥, A♣, Q♦, 5♦, 5♠\n"
        + "C7: Q♣, 3♦, J♦, 4♠, 6♣, K♠\n"
        + "C8: 8♥, 7♠, 4♦, 8♦, Q♥, 2♣";
    toString4 = "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "O6:\n"
        + "C1: 4♦, K♣, 10♠, 7♠, K♥, A♦, 6♣, 2♥, 3♠\n"
        + "C2: 6♦, 9♣, 2♦, 8♣, 8♠, 5♠, J♠, K♠, 6♠\n"
        + "C3: 4♣, 7♥, 8♦, 9♠, Q♣, Q♦, 9♦, 4♥, K♦\n"
        + "C4: 5♣, A♠, 6♥, 10♣, 5♥, 5♦, 4♠, A♣, 3♦\n"
        + "C5: J♣, 2♣, 8♥, 9♥, 7♦, 10♥, 10♦, 3♣\n"
        + "C6: A♥, J♥, Q♠, J♦, 3♥, Q♥, 7♣, 2♠";
    toString5 = "F1: A♠\n"
        + "F2: A♣\n"
        + "F3: A♥\n"
        + "F4: A♦\n"
        + "O1: 10♦\n"
        + "O2: J♥\n"
        + "O3: Q♣\n"
        + "O4: K♠\n"
        + "O5: J♦\n"
        + "O6: Q♥\n"
        + "O7: K♣\n"
        + "O8: Q♦\n"
        + "O9: K♥\n"
        + "O10: K♦\n"
        + "C1: 2♦\n"
        + "C2: 2♠\n"
        + "C3: 3♠, 2♣\n"
        + "C4: 4♠, 3♣, 2♥\n"
        + "C5: 5♠, 4♣, 3♥\n"
        + "C6: 6♠, 5♣, 4♥, 3♦\n"
        + "C7: 7♠, 6♣, 5♥, 4♦\n"
        + "C8: 8♠, 7♣, 6♥, 5♦\n"
        + "C9: 9♠, 8♣, 7♥, 6♦\n"
        + "C10: 10♠, 9♣, 8♥, 7♦\n"
        + "C11: J♠, 10♣, 9♥, 8♦\n"
        + "C12: Q♠, J♣, 10♥, 9♦";
  }

  // method that moves Cards so that theres at least 1 in each pile in a 12 cascade, 10 open pile,
  // no shuffle game
  private void everyPile() {
    model.move(PileType.CASCADE, 0, 4, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 0, 3, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 0, 2, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 0, 1, PileType.OPEN, 3);
    model.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 1, 4, PileType.OPEN, 4);
    model.move(PileType.CASCADE, 1, 3, PileType.OPEN, 5);
    model.move(PileType.CASCADE, 1, 2, PileType.OPEN, 6);
    model.move(PileType.CASCADE, 1, 1, PileType.FOUNDATION, 1);
    model.move(PileType.CASCADE, 2, 4, PileType.OPEN, 7);
    model.move(PileType.CASCADE, 2, 3, PileType.OPEN, 8);
    model.move(PileType.CASCADE, 2, 2, PileType.FOUNDATION, 2);
    model.move(PileType.CASCADE, 3, 4, PileType.OPEN, 9);
    model.move(PileType.CASCADE, 3, 3, PileType.FOUNDATION, 3);
    model.move(PileType.CASCADE, 4, 3, PileType.CASCADE, 0);
  }

  // constructor tests

  // throws an exception because the model passed to the first constructor is null
  @Test(expected = IllegalArgumentException.class)
  public void test1stConstructorNullModel() {
    new FreecellTextView(null);
  }

  // throws an exception because the model passed to the second constructor is null
  @Test(expected = IllegalArgumentException.class)
  public void test2ndConstructorNullModel() {
    new FreecellTextView(null, new StringBuilder());
  }

  // throws an exception because the appendable passed to the second constructor is null
  @Test(expected = IllegalArgumentException.class)
  public void test2ndConstructorNullAppendable() {
    new FreecellTextView(model, null);
  }

  // toString tests

  // tests that an empty string is returned because the game has not started
  @Test
  public void testToStringGameNotStarted() {
    assertEquals("", view.toString());
  }

  // tests the normal functionality of toString when a game is started with no shuffle
  @Test
  public void testToStringNormalNoShuffle() {
    model.startGame(model.getDeck(), 4, 1, false);
    assertEquals(toString1, view.toString());
    model.startGame(model.getDeck(), 10, 6, false);
    assertEquals(toString2, view.toString());
  }

  // tests the normal functionality of toString when a game is started with shuffle (simple)
  @Test
  public void testToStringNormalShuffledSimple() {
    shuffledSimple.startGame(shuffledSimple.getDeck(), 8, 4, true);
    assertEquals(toString3, shuffledViewSimple.toString());
    shuffledSimple.startGame(shuffledSimple.getDeck(), 6, 6, true);
    assertEquals(toString4, shuffledViewSimple.toString());
  }

  // tests the normal functionality of toString when a game is started with shuffle (multi)
  @Test
  public void testToStringNormalShuffledMulti() {
    shuffledMulti.startGame(shuffledMulti.getDeck(), 8, 4, true);
    assertEquals(toString3, shuffledViewMulti.toString());
    shuffledMulti.startGame(shuffledMulti.getDeck(), 6, 6, true);
    assertEquals(toString4, shuffledViewMulti.toString());
  }

  // tests the normal functionality of toString when a card is moved to each pile
  @Test
  public void testToStringEveryPile() {
    model.startGame(model.getDeck(), 12, 10, false);
    this.everyPile();
    assertEquals(toString5, view.toString());
  }

  // renderBoard tests

  // throws an exception because the Appendable stored is invalid
  @Test(expected = IOException.class)
  public void testRenderBoardInvalidAppendable() throws IOException {
    invalidView.renderBoard();
  }

  // tests the normal functionality of the renderBoard method with 4 cascades, 1 open, no shuffle
  @Test
  public void testRenderBoardNormalNoShuffle1() throws IOException {
    model.startGame(model.getDeck(), 4, 1, false);
    appendableView.renderBoard();
    assertEquals(toString1, appendable.toString());
  }

  // tests the normal functionality of the renderBoard method with 10 cascades, 6 open, no shuffle
  @Test
  public void testRenderBoardNormalNoShuffle2() throws IOException {
    model.startGame(model.getDeck(), 10, 6, false);
    appendableView.renderBoard();
    assertEquals(toString2, appendable.toString());
  }

  // tests the normal functionality of the renderBoard method with 8 cascades, 4 open, shuffle
  // (simple)
  @Test
  public void testRenderBoardNormalShuffleSimple() throws IOException {
    shuffledSimple.startGame(shuffledSimple.getDeck(), 8, 4, true);
    appendableViewSimple.renderBoard();
    assertEquals(toString3, appendable.toString());
  }

  // tests the normal functionality of the renderBoard method with 8 cascades, 4 open, shuffle
  // (multi)
  @Test
  public void testRenderBoardNormalShuffleMulti() throws IOException {
    shuffledMulti.startGame(shuffledMulti.getDeck(), 8, 4, true);
    appendableViewMulti.renderBoard();
    assertEquals(toString3, appendable.toString());
  }

  // tests the normal functionality of toString when a card is moved to each pile
  @Test
  public void testRenderBoardEveryPile() throws IOException {
    model.startGame(model.getDeck(), 12, 10, false);
    this.everyPile();
    appendableView.renderBoard();
    assertEquals(toString5, appendable.toString());
  }

  // renderMessage tests

  // throws an exception because the Appendable stored is invalid
  @Test(expected = IOException.class)
  public void testRenderMessageInvalidAppendable() throws IOException {
    invalidView.renderMessage("Exception time!");
  }

  // tests the functionality of the renderMessage method when null is passed
  @Test
  public void testRenderMessageNull() throws IOException {
    appendableView.renderMessage(null);
    assertEquals("null", appendable.toString());
  }

  // tests the normal functionality of the renderMessage method
  @Test
  public void testRenderMessageNormal() throws IOException {
    appendableView.renderMessage("Hello World!");
    assertEquals("Hello World!", appendable.toString());
  }

}