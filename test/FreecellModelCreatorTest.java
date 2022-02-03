import static org.junit.Assert.assertEquals;

import cs3500.freecell.model.FreecellModelCreator;
import cs3500.freecell.model.FreecellModelCreator.GameType;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw04.MultimoveFreecellModel;
import org.junit.Test;

/**
 * Test class for FreecellModelCreator: unit tests to ensure that the freecell creator and its
 * method function correctly.
 */
public class FreecellModelCreatorTest {

  // create tests

  // throws an exception because the game type passed in was null
  @Test(expected = IllegalArgumentException.class)
  public void testCreateNull() {
    FreecellModelCreator.create(null);
  }

  // tests the normal functionality of the create method when creating a single move model
  @Test
  public void testCreateSingle() {
    assertEquals(SimpleFreecellModel.class,
        FreecellModelCreator.create(GameType.SINGLEMOVE).getClass());
  }

  // tests the normal functionality of the create method when creating a multi move model
  @Test
  public void testCreateMulti() {
    assertEquals(MultimoveFreecellModel.class,
        FreecellModelCreator.create(GameType.MULTIMOVE).getClass());
  }
}