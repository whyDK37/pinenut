package Bridge;

/**
 * The RefinedAbstraction
 */

public class TextBold extends Text {
  private TextImp imp;

  public TextBold(String type) {
    imp = getTextImp(type);
  }

  public void DrawText(String text) {
    System.out.println(text);
    System.out.println("The text is bold text!");
    imp.DrawTextImp();
  }
}