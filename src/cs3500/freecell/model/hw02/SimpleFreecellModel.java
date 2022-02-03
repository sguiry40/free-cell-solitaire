package cs3500.freecell.model.hw02;

import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw04.AbstractFreecellModel;
import java.util.Random;

/**
 * Represents the model for a freecell solitaire game that supports single moves.
 */
public class SimpleFreecellModel extends AbstractFreecellModel {

  /**
   * Default constructor for a normal SimpleFreecellModel with no parameters.
   */
  public SimpleFreecellModel() {
    super();
  }

  /**
   * Constructs a SimpleFreecellModel that takes a Random (for testing shuffle purposes).
   *
   * @param rand the Random passed (likely with seed 1) to be assigned to the rand field
   */
  public SimpleFreecellModel(Random rand) {
    super(rand);
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) throws IllegalArgumentException, IllegalStateException {
    this.checkParams(source, pileNumber, cardIndex, destination, destPileNumber);
    if (cardIndex + 1 == this.pileSize(source, pileNumber)) {
      this.singleMove(source, pileNumber, cardIndex, destination, destPileNumber);
    } else {
      throw new IllegalArgumentException(
          "Invalid move: cannot move from card number " + cardIndex + " in " + source + " pile "
              + pileNumber + " to " + destination + " pile " + destPileNumber);
    }
  }
}
