import javax.swing.*;

public class Test  {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Proxy test client");
        JLabel label = new JLabel("Loading web page ......");
        frame.getContentPane().add(label);
        PageProxy pageProxy = new PageProxy();
        frame.getContentPane().add(pageProxy);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        //frame.pack();
        frame.setVisible(true);

        pageProxy.label.setText("aaa");
    }
}