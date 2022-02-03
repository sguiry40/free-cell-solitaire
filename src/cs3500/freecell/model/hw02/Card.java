package cs3500.freecell.model.hw02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Represents a Card in a standard 52 deck of cards. A card can have a value of 1 to 13 and one of
 * four suits: ♠, ♣, ♥, ♦
 */
public class Card {

  private final int value;
  private final String suit;
  private final String color;

  /**
   * Constructs a card used for playing in freecell solitaire.
   *
   * @param value the numerical value of the card from 1 to 13
   * @param suit  the suit of the card - either spade, club, heart, or diamond
   */

  public Card(int value, String suit) throws IllegalArgumentException {
    List<String> black = new ArrayList<>(Arrays.asList("♠", "♣"));
    List<String> red = new ArrayList<>(Arrays.asList("♥", "♦"));

    boolean containsBlack = black.contains(suit);

    if (suit == null || !(containsBlack || red.contains(suit))) {
      throw new IllegalArgumentException("Invalid suit");
    }

    this.value = value;
    this.suit = suit;

    if (containsBlack) {
      this.color = "black";
    } else {
      this.color = "red";
    }
  }

  @Override
  public String toString() {
    String result;
    switch (value) {
      case 1:
        result = "A";
        break;
      case 11:
        result = "J";
        break;
      case 12:
        result = "Q";
        break;
      case 13:
        result = "K";
        break;
      default:
        result = String.valueOf(value);
    }
    return result + suit;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof Card)) {
      return false;
    }

    Card c = (Card) o;

    return this.suit.equals(c.suit)
        && this.value == c.value
        && this.color.equals(c.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(suit, value);
  }

  /**
   * Returns true if this card is one more than the other card and this card has a different color
   * than the other card.
   *
   * @param other the card being compared to this card
   * @return true if conditions above hold true
   * @throws IllegalArgumentException if null is passed as the other card
   */
  public boolean oneMoreDiffCol(Card other) throws IllegalArgumentException {
    if (other == null) {
      throw new IllegalArgumentException("Invalid parameters");
    }
    return this.value - 1 == other.value
        && !this.color.equals(other.color);
  }

  /**
   * Returns true if this card is one less than the other card and this card has the same suit as
   * the other card.
   *
   * @param other the card being compared to this card
   * @return true if conditions above hold true
   * @throws IllegalArgumentException if null is passed as the other card
   */
  public boolean oneLessSameSuit(Card other) {
    if (other == null) {
      throw new IllegalArgumentException("Invalid parameter");
    }
    return this.value == other.value - 1
        && this.suit.equals(other.suit);
  }

  /**
   * Returns true if this card is an ace (has a value of one).
   *
   * @return true if this card's value is 1
   */
  public boolean isAce() {
    return this.value == 1;
  }

  /**
   * Gets the suit of this card.
   *
   * @return the suit of this card as a String
   */
  public String getSuit() {
    return this.suit;
  }
}
