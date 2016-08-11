/**
 *  A test client
 */
public class Test  {
    public static void main(String[] args) {
        int lineCount = 4;
        int lineWidth = 12;
        
        Context myContext = new Context();
        StrategyA strategyA = new StrategyA();
        StrategyB strategyB = new StrategyB();
        String s = "This is a test string ! This is a test string ! This is a test string ! This is a test string ! This is a test string ! This is a test string !";
        myContext.setText(s);
        myContext.setLineWidth(lineWidth);
        myContext.setStrategy(strategyA);
        myContext.drawText();

        myContext.setLineCount(lineCount);
        myContext.setStrategy(strategyB);
        myContext.drawText();
    }
}