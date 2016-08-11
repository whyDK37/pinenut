/**
 *  The RefinedAbstraction
 */
public class TextItalic extends Text {
    private TextImp imp;
    public TextItalic(String type) {
        imp = GetTextImp(type);
    }
    public void DrawText(String text) {
        System.out.println(text);
        System.out.println("The text is italic text!");
        imp.DrawTextImp();
    }
}