package Proxy;

import javax.swing.*;

public class PageProxy extends JPanel {
  public JLabel label;

  public PageProxy() {
    label = new JLabel("Loading web page !!!!!");
    this.add(label);
  }
}