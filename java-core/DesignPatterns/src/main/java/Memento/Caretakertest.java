/**
 *  A Caretaker as our test client
 */
import java.io.*;

public class CaretakerTest  {
    public static void main(String[] args) {
        TextOriginator originator = new TextOriginator();
        System.out.println("-----  Please edit text  -----");
        System.out.println("Usage: input \"undo\" to undo your edit");
        System.out.println("Usage: input \"end\" to end edit");
        try {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in));            

            String str = "";
            while(true) {
                str = in.readLine();
                if(str.equals("end")) {
                    System.out.println("-----  End edit  -----");
                    break;
                } else if(str.equals("undo")) {
                    originator.setMemento();
                } else { 
                    if(! str.equals("")) {
                        originator.addText(str);
                        originator.createMemento();
                    }
                    
                }
                System.out.println("--Input Text is :" + originator.getText());
            }
        } catch(IOException e) {
            System.out.println("I/O Error !");
            System.exit(1);
        }
    }
}