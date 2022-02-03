package cs3500.freecell.model.hw04;

import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.Card;
import java.util.Random;

/**
 * Represents the model for a freecell solitaire game that supports multimoves.
 */
public class MultimoveFreecellModel extends AbstractFreecellModel {

  public MultimoveFreecellModel() {
    super();
  }

  public MultimoveFreecellModel(Random rand) {
    super(rand);
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) throws IllegalArgumentException, IllegalStateException {
    this.checkParams(source, pileNumber, cardIndex, destination, destPileNumber);

    if (cardIndex + 1 == this.pileSize(source, pileNumber)) {
      this.singleMove(source, pileNumber, cardIndex, destination, destPileNumber);
    } else if (source == PileType.CASCADE && destination == PileType.CASCADE) {
      this.multiMove(pileNumber, cardIndex, destPileNumber);
    } else {
      throw new IllegalArgumentException(
          "Invalid move: cannot move from card number " + cardIndex + " in " + source + " pile "
              + pileNumber + " to " + destination + " pile " + destPileNumber);
    }
  }

  /**
   * Moves multiple cards from the pile number given, starting from the card index given, to the
   * destination pile given, if valid.
   *
   * @param pileNumber     the pile index to be removed from, if valid move
   * @param cardIndex      the card that begins the bunch of cards to move, if valid move
   * @param destPileNumber the pile index to be added to, if valid move
   * @throws IllegalArgumentException if the move is not valid
   */
  private void multiMove(int pileNumber, int cardIndex, int destPileNumber)
      throws IllegalArgumentException {
    if (this.validMultimove(pileNumber, cardIndex, destPileNumber)) {
      this.addAndRemovePile(pileNumber, cardIndex, destPileNumber);
    } else {
      throw new IllegalArgumentException(
          "Invalid move: not a valid move from CASCADE to CASCADE");
    }
  }

  /**
   * Checks if the pile created by the rest of the cascade pile specified (starting with the card
   * index) forms a valid build, meaning each following card is one less and a different color than
   * the card before. Also checks if the last card in the destination pile is one more and a
   * different color than the first of the bunch to move. Finally, it checks that there are enough
   * open spaces to make the move.
   *
   * @param pileNumber     the index of the cascade pile
   * @param cardIndex      the card to start at
   * @param destPileNumber the
   * @return true if the move is valid according to the standards explained above
   */
  protected boolean validMultimove(int pileNumber, int cardIndex, int destPileNumber) {
    if ((this.getNumCardsInCascadePile(pileNumber) - cardIndex) > ((this.getNumEmptyOpen() + 1)
        * Math.pow(2, this.getNumEmptyCascade()))) {
      return false;
    }
    for (int i = cardIndex; i < this.getNumCardsInCascadePile(pileNumber) - 1; i++) {
      Card card1 = this.getCascadeCardAt(pileNumber, i);
      Card card2 = this.getCascadeCardAt(pileNumber, i + 1);
      if (!(card1.oneMoreDiffCol(card2))) {
        return false;
      }
    }
    return (this.getCascadeCardAt(destPileNumber, this.getNumCardsInCascadePile(destPileNumber) - 1)
        .oneMoreDiffCol(this.getCascadeCardAt(pileNumber, cardIndex)));
  }
}

