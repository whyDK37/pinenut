import java.awt.event.*;
import javax.swing.*;

public class PageProxy extends JPanel  {
    public PageProxy() {
        label = new JLabel("Loading web page !!!!!");
        this.add(label);
    }
    public JLabel label;
}