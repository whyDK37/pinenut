package Bridge;
/**
 *  The Abstract of Text 
 */
public abstract class Text  {
    public abstract void DrawText(String text);
    protected TextImp GetTextImp(String type) {
        if(type.equals("Mac")) {
            return new TextImpMac();
        } else if(type.equals("Linux")) {
            return new TextLinuxImpl();
        } else {
            return new TextImpMac();
        }
    }
}