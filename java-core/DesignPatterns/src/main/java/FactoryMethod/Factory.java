package FactoryMethod;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: The9.com</p>
 * @author Jerry Shen
 * @version 0.5
 */

public class Factory {
    public Window CreateWindow (String type) {
        if(type.equals("Big")) {
            return new Windowbig();
        } else if(type.equals("Small")) {
            return new Windowsmall();
        } else {
            return new Windowbig();
        }
    }

    // The Main function only for our test
    public static void main(String[] args) {
        Factory myFactory = new Factory();
        Window myBigWindow = myFactory.CreateWindow("Big");
        myBigWindow.func();

        Window mySmallWindow = myFactory.CreateWindow("Small");
        mySmallWindow.func();
    }
}