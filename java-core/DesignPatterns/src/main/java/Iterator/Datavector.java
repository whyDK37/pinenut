package Iterator;
/**
 *  Data stored in a vector
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class DataVector implements Aggregate {
    private Vector data = new Vector();
    
    public DataVector(String fileName) {
        try {
            BufferedReader f = new BufferedReader(new FileReader(fileName));
            String s = f.readLine();
            while(s != null) {
                if(s.trim().length() > 0) {
                    data.add(s);
                }
                s = f.readLine();
            }
            f.close();
        } catch (FileNotFoundException e) {
            System.out.println("Can not find such file !");
        } catch (IOException e) {
            System.out.println("I/O Error !");
            System.exit(0);
        }
    }
    
    public Iterator CreateIterator() {
        return new VectorIterator(data);
    }
    
}