import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.freecell.model.hw02.Card;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Card: unit tests to ensure that Cards and their methods function correctly.
 */

public class CardTest {

  private Card nineOfHearts;
  private Card aceOfSpades;
  private Card kingOfDiamonds;
  private Card queenOfClubs;
  private Card jackOfHearts;
  private Card nineOfHeartsAlias;
  private Card jackOfHeartsCopy;
  private Card tenOfDiamonds;
  private Card eightOfHearts;

  // initialize examples
  @Before
  public void setup() {
    nineOfHearts = new Card(9, "♥");
    aceOfSpades = new Card(1, "♠");
    kingOfDiamonds = new Card(13, "♦");
    queenOfClubs = new Card(12, "♣");
    jackOfHearts = new Card(11, "♥");
    nineOfHeartsAlias = nineOfHearts;
    jackOfHeartsCopy = new Card(11, "♥");
    tenOfDiamonds = new Card(10, "♦");
    eightOfHearts = new Card(8, "♥");
  }

  // tests an exception is thrown when null is passed as suit
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullSuit() {
    new Card(4, null);
  }

  // tests an exception is thrown when a string other than expected suits is passed
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorIllegalSuit() {
    new Card(12, "Hello");
  }

  // tests the normal functionality of the overridden toString method,
  // reaching every switch case
  @Test
  public void testToString() {
    assertEquals("9♥", nineOfHearts.toString());
    assertEquals("A♠", aceOfSpades.toString());
    assertEquals("K♦", kingOfDiamonds.toString());
    assertEquals("Q♣", queenOfClubs.toString());
    assertEquals("J♥", jackOfHearts.toString());
  }

  // test for overridden equals method that tests the intensional equality branch
  @Test
  public void testEqualsIntensional() {
    assertTrue(nineOfHearts.equals(nineOfHearts));
    assertTrue(nineOfHearts.equals(nineOfHeartsAlias));
  }

  // test for overridden equals method that tests the extensional equality branch
  @Test
  public void testEqualsExtensional() {
    assertTrue(jackOfHearts.equals(jackOfHeartsCopy));
    assertFalse(queenOfClubs.equals(kingOfDiamonds));
  }

  // test for overridden equals method that tests two different objects
  @Test
  public void testEqualsInstanceOf() {
    assertFalse(jackOfHearts.equals("Hello"));
    assertFalse(aceOfSpades.equals(new ArrayList<>()));
  }

  // tests for overridden hashCode method's basic functionality
  @Test
  public void testHashCode() {
    assertEquals(305669, nineOfHearts.hashCode());
    assertEquals(305610, queenOfClubs.hashCode());
  }

  // tests that an exception is thrown when null is passed as a parameter
  // to the oneMoreDiffCol method
  @Test(expected = IllegalArgumentException.class)
  public void testOneMoreDiffColNull() {
    nineOfHearts.oneMoreDiffCol(null);
  }

  // tests the normal functionality for the oneMoreDiffCol method
  @Test
  public void testOneMoreDiffCol() {
    assertTrue(kingOfDiamonds.oneMoreDiffCol(queenOfClubs)); // one less, different color
    assertFalse(aceOfSpades.oneMoreDiffCol(nineOfHearts)); // not one less, different color
    assertFalse(jackOfHearts.oneMoreDiffCol(tenOfDiamonds)); // one less, same color
    assertFalse(nineOfHearts.oneMoreDiffCol(jackOfHearts)); // not one less, same color
  }

  // tests that an exception is thrown when null is passed as a parameter
  // to the oneLessSameSuit method
  @Test(expected = IllegalArgumentException.class)
  public void testOneLessSameSuitNull() {
    nineOfHearts.oneLessSameSuit(null);
  }

  // tests the normal functionality for the oneLessSameSuit method
  @Test
  public void testOneLessSameSuit() {
    assertTrue(eightOfHearts.oneLessSameSuit(nineOfHearts)); // one less, same suit
    assertFalse(nineOfHearts.oneLessSameSuit(jackOfHearts)); // not one less, same suit
    assertFalse(queenOfClubs.oneLessSameSuit(kingOfDiamonds)); // one less, different suit
    assertFalse(aceOfSpades.oneLessSameSuit(eightOfHearts)); // not one less, different suit
  }

  // tests the normal functionality for the isAce method
  @Test
  public void testIsAce() {
    assertTrue(aceOfSpades.isAce());
    assertFalse(eightOfHearts.isAce());
  }

  // tests the normal functionality of the getSuit method
  @Test
  public void testGetSuit() {
    assertEquals("♥", nineOfHearts.getSuit());
    assertEquals("♣", queenOfClubs.getSuit());
  }
}