package cs3500.freecell.view;

import cs3500.freecell.model.FreecellModelState;
import java.io.IOException;

/**
 * Represents the text view for a game of freecell solitaire as a String.
 */
public class FreecellTextView implements FreecellView {

  private final FreecellModelState<?> model;
  private Appendable out;

  /**
   * Constructs a FreecellTextView.
   *
   * @param model the FreecellModelState to be used
   */

  public FreecellTextView(FreecellModelState<?> model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
  }

  /**
   * Constructs a FreecellTextView with an Appendable.
   *
   * @param model the FreecellModelState to be used
   * @param out   the Appendable object to be used
   */
  public FreecellTextView(FreecellModelState<?> model, Appendable out) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    } else if (out == null) {
      throw new IllegalArgumentException("Appendable cannot be null");
    }
    this.model = model;
    this.out = out;
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    if (model.getNumCascadePiles() == -1) {
      return result.toString();
    }
    for (int i = 0; i < 4; i++) {
      result.append("F").append(i + 1).append(":");
      for (int j = 0; j < model.getNumCardsInFoundationPile(i); j++) {
        if (j == model.getNumCardsInFoundationPile(i) - 1) {
          result.append(" ").append(model.getFoundationCardAt(i, j).toString());
        } else {
          result.append(" ").append(model.getFoundationCardAt(i, j).toString()).append(",");
        }
      }
      result.append("\n");
    }
    for (int i = 0; i < model.getNumOpenPiles(); i++) {
      result.append("O").append(i + 1).append(":");
      if (model.getNumCardsInOpenPile(i) == 1) {
        result.append(" ").append(model.getOpenCardAt(i).toString());
      }
      result.append("\n");
    }
    for (int i = 0; i < model.getNumCascadePiles(); i++) {
      result.append("C").append(i + 1).append(":");
      for (int j = 0; j < model.getNumCardsInCascadePile(i); j++) {
        if (j == model.getNumCardsInCascadePile(i) - 1) {
          result.append(" ").append(model.getCascadeCardAt(i, j).toString());
        } else {
          result.append(" ").append(model.getCascadeCardAt(i, j).toString()).append(",");
        }
      }
      if (i != model.getNumCascadePiles() - 1) {
        result.append("\n");
      }
    }
    return result.toString();
  }

  @Override
  public void renderBoard() throws IOException {
    out.append(this.toString());
  }

  @Override
  public void renderMessage(String message) throws IOException {
    out.append(message);
  }
}
