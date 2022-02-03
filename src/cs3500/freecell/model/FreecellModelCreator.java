package cs3500.freecell.model;

import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw04.MultimoveFreecellModel;

/**
 * Class containing an enum with the two types of freecell solitaire models and a method to creat
 * each of these models.
 */
public class FreecellModelCreator {

  /**
   * Enum used to determine the type of freecell model to create, SINGLEMOVE for simple model,
   * MULTIMOVE for multimove model.
   */
  public enum GameType { SINGLEMOVE, MULTIMOVE }

  /**
   * factory method that returns which ever model of freecell solitaire is specified by the use.
   *
   * @param type the game type (either single move or multi move)
   * @return an instance of the requested model
   * @throws IllegalArgumentException if the given GameType is null
   */
  public static FreecellModel<Card> create(GameType type) throws IllegalArgumentException {
    if (type == null) {
      throw new IllegalArgumentException("Type cannot be null");
    }
    if (type == GameType.SINGLEMOVE) {
      return new SimpleFreecellModel();
    } else if (type == GameType.MULTIMOVE) {
      return new MultimoveFreecellModel();
    } else {
      throw new IllegalArgumentException("Invalid type");
    }
  }

}
