/**
 *  A concrete class extends AbstractRead
 *  This class can read from a file
 */
import java.io.*;

public class ReadFile extends AbstractRead {
    private BufferedReader in = null;
    public ReadFile() {
    }
    public ReadFile(String fileName) {
        resource = fileName;
    }
    protected boolean open() {
        try {
            in = new BufferedReader(new FileReader(resource));
        } catch(IOException e) {
            System.out.println("Can not open file!");
            return false;
        }
        return true;
    }
    protected void readContent() {
        try {
            if(in != null) {
                String str;
                while((str = in.readLine()) != null) {
                     System.out.println(str);  
                }
            }
        } catch(IOException e) {
            System.out.println("Read file error !");
        }
    }
    protected void close() {
        if(in != null) {
            try {
                in.close();
            } catch(IOException e) {
                System.out.println("IO error !");
            }
        }
    }
}