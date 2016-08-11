/**
 *  An abstract class which can get content from a file or a HTTP URL 
 *  or other resource  
 */
public abstract class AbstractRead {
    protected String resource;
    public void getContent() { // Template Method
        if(open()) {
            readContent();
            close();
        }
    }
    public void setResource(String s) {
        resource = s;
    }
    protected abstract boolean open();
    protected abstract void readContent();
    protected abstract void close();
}