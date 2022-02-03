import static org.junit.Assert.assertEquals;

import cs3500.freecell.controller.FreecellController;
import cs3500.freecell.controller.SimpleFreecellController;
import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.FreecellModelCreator;
import cs3500.freecell.model.FreecellModelCreator.GameType;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw04.MultimoveFreecellModel;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for SimpleFreecellController: unit tests to ensure that the freecell controller and
 * its methods all function correctly.
 */
public abstract class SimpleFreecellControllerTest {

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
  public static final class SimpleFreecellModelCreateTest extends SimpleFreecellControllerTest {

    @Override
    protected FreecellModel<Card> create() {
      return FreecellModelCreator.create(GameType.SINGLEMOVE);
    }
  }

  /**
   * Concrete class for testing MultimoveFreecellModel implementation of FreecellModel.
   */
  public static final class MultimoveFreecellModelCreateTest extends SimpleFreecellControllerTest {

    @Override
    protected FreecellModel<Card> create() {
      return FreecellModelCreator.create(GameType.MULTIMOVE);
    }
  }

  private FreecellModel<Card> model;
  private FreecellModel<Card> modelMulti;
  private FreecellModel<Card> shuffledSimple;
  private FreecellModel<Card> shuffledMulti;
  private Appendable appendable;
  private ControllerExamples examples;

  private List<Card> standardDeck;

  private StringBuilder dontCareOutput;
  private StringBuilder log;
  private FreecellModel<Card> mockModel;

  @Before
  public void setup() {
    model = create();
    modelMulti = new MultimoveFreecellModel();
    shuffledSimple = new SimpleFreecellModel(new Random(1));
    shuffledMulti = new MultimoveFreecellModel(new Random(1));
    appendable = new StringBuilder();
    examples = new ControllerExamples();

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

    dontCareOutput = new StringBuilder();
    log = new StringBuilder();
    mockModel = new ConfirmInputModel(log);
  }

  // constructor tests

  // throws an exception because the model passed was null
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorModelNull() {
    new SimpleFreecellController(null, new StringReader(""), new StringBuilder());
  }

  // throws an exception because the readable passed was null
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorReadableNull() {
    new SimpleFreecellController(model, null, new StringBuilder());
  }

  // throws an exception because the appendable passed was null
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAppendableNull() {
    new SimpleFreecellController(model, new StringReader(""), null);
  }

  // playGame tests

  // throws an exception because null is passed as the deck
  @Test(expected = IllegalArgumentException.class)
  public void testPlayGameNullDeck() {
    new SimpleFreecellController(model, new StringReader(""), new StringBuilder())
        .playGame(null, 8, 4, false);
  }

  // throws an exception because the readable object has nothing left to read
  @Test(expected = IllegalStateException.class)
  public void testPlayGameNoMoreReadable() {
    new SimpleFreecellController(model, new StringReader(""), new StringBuilder())
        .playGame(standardDeck, 8, 4, false);
  }

  // throws an exception because the appendable object is faulty/invalid
  @Test(expected = IllegalStateException.class)
  public void testPlayGameInvalidAppendable() {
    new SimpleFreecellController(model, new StringReader("C1 7 O1"), new InvalidAppendable())
        .playGame(standardDeck, 8, 4, false);
  }

  // tests that the game can't be started when a faulty deck is given to playGame
  @Test
  public void testPlayGameFaultyDeck() {
    new SimpleFreecellController(model, new StringReader("C1 7 O1"), appendable)
        .playGame(new ArrayList<>(Collections.singletonList(new Card(1, "♠"))),
            8, 4, false);
    assertEquals("Could not start game.", appendable.toString());
  }

  // tests that the game can't be started when the number of cascade piles is too small
  @Test
  public void testPlayGameCascadeTooSmall() {
    new SimpleFreecellController(model, new StringReader("C1 7 O1"), appendable)
        .playGame(standardDeck, 3, 4, false);
    assertEquals("Could not start game.", appendable.toString());
  }

  // tests that the game can't be started when the number of cascade piles is too large
  @Test
  public void testPlayGameCascadeTooLarge() {
    new SimpleFreecellController(model, new StringReader("C1 7 O1"), appendable)
        .playGame(standardDeck, 53, 4, false);
    assertEquals("Could not start game.", appendable.toString());
  }

  // tests that the game can't be started when the number of open piles is too small
  @Test
  public void testPlayGameOpenTooSmall() {
    new SimpleFreecellController(model, new StringReader("C1 7 O1"), appendable)
        .playGame(standardDeck, 8, 0, false);
    assertEquals("Could not start game.", appendable.toString());
  }

  // tests that the game can't be started when the number of open piles is too large
  @Test
  public void testPlayGameOpenTooLarge() {
    new SimpleFreecellController(model, new StringReader("C1 7 O1"), appendable)
        .playGame(standardDeck, 8, 53, false);
    assertEquals("Could not start game.", appendable.toString());
  }

  // tests the normal functionality of playGame when a q is in the first spot with
  // two other valid inputs
  @Test
  public void testPlayGameQInFirstSpot() {
    new SimpleFreecellController(model, new StringReader("q 7 O1"), appendable)
        .playGame(standardDeck, 8, 4, false);
    assertEquals(examples.toString1, appendable.toString());
  }

  // tests the normal functionality of playGame when a q is in the second spot with
  // two other valid inputs
  @Test
  public void testPlayGameQInSecondSpot() {
    new SimpleFreecellController(model, new StringReader("C1 Q O1"), appendable)
        .playGame(standardDeck, 8, 4, false);
    assertEquals(examples.toString1, appendable.toString());
  }

  // tests the normal functionality of playGame when a q is in the third spot with
  // two other valid inputs
  @Test
  public void testPlayGameQInThirdSpot() {
    new SimpleFreecellController(model, new StringReader("C1 7 q"), appendable)
        .playGame(standardDeck, 8, 4, false);
    assertEquals(examples.toString1, appendable.toString());
  }

  // tests the normal functionality of playGame when a valid move is given, and nothing else
  // (with quit after to avoid exception)
  @Test
  public void testPlayGameValidMove() {
    new SimpleFreecellController(model, new StringReader("C1 7 O1 q"), appendable)
        .playGame(standardDeck, 8, 4, false);
    assertEquals(examples.toString2, appendable.toString());
  }

  // tests the normal functionality of playGame when a valid multimove is given, and nothing else
  // (with quit after to avoid exception)
  @Test
  public void testPlayGameValidMultimove() {
    new SimpleFreecellController(modelMulti, new StringReader(examples.multimove), appendable)
        .playGame(standardDeck, 8, 15, false);
    assertEquals(examples.toString18, appendable.toString());
  }

  // tests the normal functionality of playGame when a valid move is given with lowercase inputs,
  // and nothing else (with quit after to avoid exception)
  @Test
  public void testPlayGameValidMoveLowercase() {
    new SimpleFreecellController(model, new StringReader("c1 7 o1 q"), appendable)
        .playGame(standardDeck, 8, 4, false);
    assertEquals(examples.toString2, appendable.toString());
  }

  // tests the normal functionality of playGame when a valid move is given in between bad input
  // (with quit after to avoid exception)
  @Test
  public void testPlayGameValidMoveInBetweenBadInput() {
    new SimpleFreecellController(model,
        new StringReader("I named my cat C1 because he likes to eat 7 apples before O1 q"),
        appendable)
        .playGame(standardDeck, 8, 4, false);
    assertEquals(examples.toString3, appendable.toString());
  }

  // tests the normal functionality of playGame when an invalid move is given in just 3 inputs
  // (with quit after to avoid exception)
  @Test
  public void testPlayGameInvalidMove() {
    new SimpleFreecellController(model, new StringReader("C1 7 F1 q"), appendable)
        .playGame(standardDeck, 8, 4, false);
    assertEquals(examples.toString4, appendable.toString());
  }

  // tests the normal functionality of playGame when an invalid move is given in between bad input
  // (with quit after to avoid exception)
  @Test
  public void testPlayGameInvalidMoveInBetweenBadInput() {
    new SimpleFreecellController(model,
        new StringReader("I named my cat C1 because he likes to eat 7 apples before F1 q"),
        appendable)
        .playGame(standardDeck, 8, 4, false);
    assertEquals(examples.toString5, appendable.toString());
  }

  // tests the normal functionality of playGame when no good input is entered and it asks for
  // source again (with quit after to avoid exception)
  @Test
  public void testPlayGameNoGoodInput() {
    new SimpleFreecellController(model, new StringReader("hello q"), appendable)
        .playGame(standardDeck, 8, 4, false);
    assertEquals(examples.toString6, appendable.toString());
  }

  // tests the normal functionality of playGame when 1 good input is entered and it asks for
  // card index again (with quit after to avoid exception)
  @Test
  public void testPlayGame1GoodInput() {
    new SimpleFreecellController(model, new StringReader("C1 hello q"), appendable)
        .playGame(standardDeck, 8, 4, false);
    assertEquals(examples.toString7, appendable.toString());
  }

  // tests the normal functionality of playGame when 2 good input is entered and it asks for
  // destination again (with quit after to avoid exception)
  @Test
  public void testPlayGame2GoodInput() {
    new SimpleFreecellController(model, new StringReader("C1 7 hello q"), appendable)
        .playGame(standardDeck, 8, 4, false);
    assertEquals(examples.toString8, appendable.toString());
  }

  // tests the normal functionality of playGame when a bad input is entered after a valid
  // move and it asks for source again (with quit to avoid exception)
  @Test
  public void testPlayGameBadInputAfterMove() {
    new SimpleFreecellController(model, new StringReader("C1 7 O1 hello q"), appendable)
        .playGame(standardDeck, 8, 4, false);
    assertEquals(examples.toString9, appendable.toString());
  }

  // tests the normal functionality of playGame when a bad input is entered after a valid
  // move and 1 good input and it asks for card index again (with quit to avoid exception)
  @Test
  public void testPlayGameBadInputAfterMoveAndSource() {
    new SimpleFreecellController(model, new StringReader("C1 7 O1 C1 hello q"), appendable)
        .playGame(standardDeck, 8, 4, false);
    assertEquals(examples.toString10, appendable.toString());
  }

  // tests the normal functionality of playGame when a bad input is entered after a valid
  // move and 2 good input and it asks for destination again (with quit to avoid exception)
  @Test
  public void testPlayGameBadInputAfterMoveAndCardIndex() {
    new SimpleFreecellController(model, new StringReader("C1 7 O1 C1 6 hello q"), appendable)
        .playGame(standardDeck, 8, 4, false);
    assertEquals(examples.toString11, appendable.toString());
  }

  // tests the normal functionality of playGame when two valid moves are entered
  // after each other (with quit to avoid exception)
  @Test
  public void testPlayGameTwoValidMoves() {
    new SimpleFreecellController(model, new StringReader("C1 7 O1 C1 6 O2 q"), appendable)
        .playGame(standardDeck, 8, 4, false);
    assertEquals(examples.toString12, appendable.toString());
  }

  // tests that the game is setup correctly when shuffle is passed to playGame (simple model)
  @Test
  public void testPlayGameShuffleSimple() {
    new SimpleFreecellController(shuffledSimple, new StringReader("q"), appendable)
        .playGame(standardDeck, 8, 4, true);
    assertEquals(examples.toString13, appendable.toString());
  }

  // tests that the game is setup correctly when shuffle is passed to playGame (multimove model)
  @Test
  public void testPlayGameShuffleMultimove() {
    new SimpleFreecellController(shuffledMulti, new StringReader("q"), appendable)
        .playGame(standardDeck, 8, 4, true);
    assertEquals(examples.toString13, appendable.toString());
  }

  // tests the normal functionality of the playGame method when the game is won
  @Test
  public void testPlayGameGameWon() {
    new SimpleFreecellController(model, new StringReader(examples.winGame), appendable)
        .playGame(standardDeck, 52, 4, false);
    assertEquals(examples.toString14, appendable.toString());
  }

  // playGame tests with mock model to test controller is sending correct parameters to model

  // tests that startGame and move receive the right inputs after a valid move is entered
  // (with quit after to avoid exception)
  @Test
  public void testPlayGameMockValidMove() {
    Reader in = new StringReader("C1 7 O1 q");
    FreecellController<Card> controller = new SimpleFreecellController(mockModel, in,
        dontCareOutput);

    controller.playGame(standardDeck, 8, 4, false);
    assertEquals(examples.toString15, log.toString());
  }

  // tests that startGame and move receive the right inputs after a valid move is entered with
  // bad input in between (with quit after to avoid exception)
  @Test
  public void testPlayGameMockValidMoveInBetweenBadInput() {
    Reader in =
        new StringReader("I named my cat C1 because he likes to eat 7 apples before O1 q");
    FreecellController<Card> controller = new SimpleFreecellController(mockModel, in,
        dontCareOutput);

    controller.playGame(standardDeck, 8, 4, false);
    assertEquals(examples.toString15, log.toString());
  }

  // tests that startGame and move receive the right inputs after an invalid move is entered
  // (with quit after to avoid exception)
  @Test
  public void testPlayGameMockInvalidMove() {
    Reader in = new StringReader("C1 7 F1 q");
    FreecellController<Card> controller = new SimpleFreecellController(mockModel, in,
        dontCareOutput);

    controller.playGame(standardDeck, 8, 4, false);
    assertEquals(examples.toString16, log.toString());
  }

  // tests that startGame and move receive the right inputs after an invalid move is entered with
  // bad input in between (with quit after to avoid exception)
  @Test
  public void testPlayGameMockInvalidMoveInBetweenBadInput() {
    Reader in =
        new StringReader("I named my cat C1 because he likes to eat 7 apples before F1 q");
    FreecellController<Card> controller = new SimpleFreecellController(mockModel, in,
        dontCareOutput);

    controller.playGame(standardDeck, 8, 4, false);
    assertEquals(examples.toString16, log.toString());
  }

  // tests that startGame and move receive the right inputs after two valid moves
  // are entered at once (with quit after to avoid exception)
  @Test
  public void testPlayGameMockTwoValidMoves() {
    Reader in = new StringReader("C1 7 O1 C1 6 O2 q");
    FreecellController<Card> controller = new SimpleFreecellController(mockModel, in,
        dontCareOutput);

    controller.playGame(standardDeck, 8, 4, false);
    assertEquals(examples.toString17, log.toString());
  }
}