/**
 *  The RefinedAbstraction
 */
import java.io.*;

public class TextBold extends Text {
    private TextImp imp;
    public TextBold(String type) {
        imp = GetTextImp(type);
    }
    public void DrawText(String text) {
        System.out.println(text);
        System.out.println("The text is bold text!");
        imp.DrawTextImp();
    }
}