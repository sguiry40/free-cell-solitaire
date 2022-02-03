import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.Card;
import java.util.List;
import java.util.Objects;

/**
 * Mock class of the model class for freecell solitaire used to test the communication between the
 * controller and model.
 */
public class ConfirmInputModel implements FreecellModel<Card> {

  final StringBuilder log;

  /**
   * Creates a mock of the freecell solitaire model.
   *
   * @param log the StribgBuilder to be stored
   */
  public ConfirmInputModel(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  @Override
  public List<Card> getDeck() {
    return null;
  }

  @Override
  public void startGame(List<Card> deck, int numCascadePiles, int numOpenPiles, boolean shuffle)
      throws IllegalArgumentException {
    log.append(String.format("startGame called.\ndeck = " + deck + ", numCascadePiles = %d, "
            + "numOpenPiles = %d, shuffle = %b\n",
        numCascadePiles, numOpenPiles, shuffle));
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) throws IllegalArgumentException, IllegalStateException {
    log.append(
        String.format("move called.\nsource = " + source + ", pileNumber = %d, cardIndex = %d,"
            + " destination = " + destination
            + ", destPileNumber = %d\n", pileNumber, cardIndex, destPileNumber));
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public int getNumCardsInFoundationPile(int index)
      throws IllegalArgumentException, IllegalStateException {
    return 0;
  }

  @Override
  public int getNumCascadePiles() {
    return 0;
  }

  @Override
  public int getNumCardsInCascadePile(int index)
      throws IllegalArgumentException, IllegalStateException {
    return 0;
  }

  @Override
  public int getNumCardsInOpenPile(int index)
      throws IllegalArgumentException, IllegalStateException {
    return 0;
  }

  @Override
  public int getNumOpenPiles() {
    return 0;
  }

  @Override
  public Card getFoundationCardAt(int pileIndex, int cardIndex)
      throws IllegalArgumentException, IllegalStateException {
    return null;
  }

  @Override
  public Card getCascadeCardAt(int pileIndex, int cardIndex)
      throws IllegalArgumentException, IllegalStateException {
    return null;
  }

  @Override
  public Card getOpenCardAt(int pileIndex) throws IllegalArgumentException, IllegalStateException {
    return null;
  }
}
