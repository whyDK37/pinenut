/**
 *  Our test Originator class with Memento class.
 *  Put 2 class into 1 file, so Memento member can be  visited by Originator 
 */
import java.io.*;
import java.util.*;

public class TextOriginator  {
    private StringBuffer strbuf = new StringBuffer();
    private String state = "";
    private TextMemento memo;
    public TextOriginator() {
        memo = new TextMemento();
    }
    public void addText(String addStr) {
        state = addStr;
        strbuf.append(addStr);
    }
    public void createMemento() {
        memo.addMemo(state);
    }
    public void setMemento() {
        String tmp = memo.getState();
        if(tmp != null) {            
            int start = strbuf.length() - tmp.length();
            int end = strbuf.length();
            if(start >= 0) {
                strbuf.delete(start,  end);
            } else {
                System.out.println("Get Memo Error !");
            }
        }
    }
    public String getText() {
        return strbuf.toString();
    }
}

class TextMemento {
    //String state;
    int i = 0;
    LinkedList memoList;
    
    TextMemento() {
        //state = null;
        memoList = new LinkedList();
    }
    void addMemo(String state) {
        memoList.addLast(state);
    }
    String getState() {
        if(memoList.size() != 0) {
            String tmp = (String)memoList.getLast();
            memoList.removeLast();
            return tmp;
        } else {
            return null;
        }      
    }
}